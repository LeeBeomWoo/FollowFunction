package bodygate.bcns.bodygation.support

import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider

interface FitConnect{
    val TAG: String
        get() = "FitConnect"
    var account:GoogleSignInAccount
    var  mAuth: FirebaseAuth

    fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)

            // Signed in successfully, show authenticated UI.
            accessGoogleFit(account)
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.statusCode)
            getProfileInformation(null)
        }

    }
    private fun getProfileInformation(acct: FirebaseUser?) {
        //if account is not null fetch the information
        if (acct != null) {
            //user display name
            val personName = acct.getDisplayName()


            //user email id
            val personEmail = acct.getEmail()

            Log.i("profile", "getProfileInformation : " + personName + "\t" + acct.photoUrl.toString() + "\t"+ personEmail +"\t" + acct.toString())
        }
    }

    fun accessGoogleFit(acc:GoogleSignInAccount){
        val credential = GoogleAuthProvider.getCredential(acc.idToken, null)
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(object : OnCompleteListener<AuthResult>{
                    override fun onComplete(p0: Task<AuthResult>) {
                        if(p0.isSuccessful){
                            val user = mAuth.currentUser
                            getProfileInformation(user!!)
                        }else{
                            getProfileInformation(null)
                        }
                    }
                })
    }
}