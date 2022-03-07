package com.rootdown.dev.adidevibm.model.feature_random_user.repo

import android.util.Log
import androidx.lifecycle.LiveData
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.rootdown.dev.adidevibm.model.feature_random_user.db.AppDatabase
import com.rootdown.dev.adidevibm.model.feature_random_user.db.Users
import com.rootdown.dev.adidevibm.model.feature_random_user.net.UserService
import com.rootdown.dev.adidevibm.model.feature_random_user.net.UserServiceImpl
import com.rootdown.dev.adidevibm.model.feature_random_user.repo.util.ResultsDeserializer
import com.rootdown.dev.adidevibm.model.feature_random_user.repo.util.ResultsSerializer
import com.rootdown.dev.adidevibm.model.feature_random_user.repo.util.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class UserRepoImpl(
    val api: UserServiceImpl,
    private val db: AppDatabase,
) {

    suspend fun userDB() {
        withContext(Dispatchers.IO){
            val user = api.getuser()
            val xi = user.toString().removeRange(0..11)
            val xix = xi.replaceAfter("]","").replace("]","", false)

            val convertJson = Gson().toJson(user)
            val output = Gson().fromJson(user, User::class.java)
            val gSon = GsonBuilder().registerTypeAdapter(User::class.java, ResultsDeserializer()).create()
            val xSon = GsonBuilder().registerTypeAdapter(User::class.java, ResultsSerializer()).create()
            val out = xSon.toJson(user)
            val u = gSon.fromJson<User>(xix,User::class.java)

            Log.w("...",u.toString())
            val currUser = Users(
                uid = u.id,
                name = u.name,
                location = u.location,
                login = u.login,
                dob = u.dob,
                registered = u.registered,
                picture = u.picture,
                gender = u.gender,
                email = u.email,
                phone = u.phone,
                cell = u.cell,
                nat = u.nat,
            )
            val ls: List<Users> = listOf(currUser)
            db.userDao().insertUser(ls)
        }
    }
    fun getUsers(): LiveData<List<Users>> {
        return db.userDao().getUsers()
    }
}


