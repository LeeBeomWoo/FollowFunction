package com.example.followfunction.vimeo.networking

import android.content.Context
import com.example.followfunction.vimeo.AccountPreferenceManager
import com.vimeo.networking.AccountStore
import com.vimeo.networking.VimeoClient
import com.vimeo.networking.model.VimeoAccount

class TestAccountStore(context: Context?) : AccountStore {
    override fun saveAccount(vimeoAccount: VimeoAccount?, email: String?) {
        AccountPreferenceManager.clientAccount = vimeoAccount
    }

    override fun saveAccount(vimeoAccount: VimeoAccount?, email: String?, password: String?) {
        AccountPreferenceManager.clientAccount = vimeoAccount
    }

    // NOTE: You can use the account manager in the below methods to hook into the Android Accounts
    // @RequiresPermission(Manifest.permission.GET_ACCOUNTS)
    // val mAccountManager: AccountManager? = AccountManager.get(context!!)

    override fun loadAccount() =
        AccountPreferenceManager.clientAccount

    override fun deleteAccount(vimeoAccount: VimeoAccount) {
        AccountPreferenceManager.removeClientAccount()
        // NOTE: You'll now need a client credentials grant (without an authenticated user)
    }

    fun updateAccount(vimeoAccount: VimeoAccount) {
        AccountPreferenceManager.clientAccount = vimeoAccount
        VimeoClient.getInstance().vimeoAccount = vimeoAccount
    }

}