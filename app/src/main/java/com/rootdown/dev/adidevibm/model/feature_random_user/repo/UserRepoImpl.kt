package com.rootdown.dev.adidevibm.model.feature_random_user.repo

import android.util.Log
import androidx.lifecycle.LiveData
import com.google.gson.GsonBuilder
import com.rootdown.dev.adidevibm.model.feature_random_user.db.AppDatabase
import com.rootdown.dev.adidevibm.model.feature_random_user.db.Users
import com.rootdown.dev.adidevibm.model.feature_random_user.net.UserService
import com.rootdown.dev.adidevibm.model.feature_random_user.net.UserServiceImpl
import com.rootdown.dev.adidevibm.model.feature_random_user.repo.util.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepoImpl(
    val api: UserServiceImpl,
    private val db: AppDatabase,
) {

    suspend fun userDB() {
        withContext(Dispatchers.IO){
            val user = api.getuser()
            val xi = user.toString().removeRange(0..11)
            val xix = xi.replaceAfter("]","").replace("]","", false)

            //val convertJson = Gson().toJson(user)
            //val output = Gson().fromJson(user, User::class.java)
            val gSon = GsonBuilder().registerTypeAdapter(User::class.java, ResultsDeserializer()).create()
            val xSon = GsonBuilder().registerTypeAdapter(User::class.java, ResultsSerializer()).create()
            //val out = xSon.toJson(user)
            val u = gSon.fromJson<User>(xix,User::class.java)

            //register name and picture type adapters
            val nameGson = GsonBuilder().registerTypeAdapter(Name::class.java,NameDeserializer()).create()
            val picGson = GsonBuilder().registerTypeAdapter(Picture::class.java,PictureDeserializer()).create()
            val name = nameGson.fromJson<Name>(u.name,Name::class.java)
            val pic = picGson.fromJson<Picture>(u.picture,Picture::class.java)

            val nm = name.first?.replace("\"", "")
            val picture = pic.thumbnail?.replace("\"", "")

            Log.w("...",u.toString())
            val currUser = Users(
                uid = u.id,
                name = nm,
                location = u.location,
                login = u.login,
                dob = u.dob,
                registered = u.registered,
                picture = picture,
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
    val getUsers: LiveData<List<Users>> = db.userDao().getUsers()
}


