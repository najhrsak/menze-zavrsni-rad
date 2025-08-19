package hr.tvz.android_menza

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MenzeActivity : AppCompatActivity() {
    private lateinit var newRecyclerview : RecyclerView
    private lateinit var newArrayList: MutableList<Menza>
    private lateinit var updatedArrayList: MutableList<Menza>
    private lateinit var client : RestService
    private var tokenRefresher: TokenRefresher = TokenRefresher()
    private val searchText: EditText
        get() = findViewById(R.id.search)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menze)
        client = APIService().getService(RestService::class.java)

        newRecyclerview = findViewById(R.id.recView)
        newRecyclerview.layoutManager = LinearLayoutManager(this)
        newRecyclerview.hasFixedSize()
        tokenRefresher.checkAndRefreshToken(client)
        client = APIService().getServiceWithToken(RestService::class.java, Token.accessToken.toString())
        var menzeBackend: Call<MutableList<Menza>> = client.getMenze()
        menzeBackend.enqueue(object: Callback<MutableList<Menza>> {
            override fun onResponse(
                call: retrofit2.Call<MutableList<Menza>>,
                response: Response<MutableList<Menza>>
            ) {
                if (response.isSuccessful) {
                    newArrayList = response.body()!!
                    updatedArrayList = newArrayList
                    getUserData()
                }
                else{
                    println("ERROR:::::::" + response.message())
                }
            }

            override fun onFailure(call: retrofit2.Call<MutableList<Menza>>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }
        })
        searchText.doOnTextChanged{
                text, _, _, _ ->
            run {
                if (text != null) {
                    updatedArrayList = newArrayList.filter {
                        it.adresa.contains(text)
                    }.toMutableList()
                    getUserData()
                }
            }
        }
    }

    private fun getUserData(){
        var adapter = MyAdapter(updatedArrayList)
        newRecyclerview.adapter = adapter

        adapter.setOnItemClickListener(object : MyAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                val intent = Intent(this@MenzeActivity, OdabranaMenzaActivity::class.java)
                intent.putExtra("nazivMenze", newArrayList[position].naziv)
                intent.putExtra("adresaMenze", newArrayList[position].adresa)
                intent.putExtra("radnoVrijemeMenze", newArrayList[position].radnoVrijeme)
                intent.putExtra("infoMenze", newArrayList[position].info)
                intent.putExtra("meniMenze", newArrayList[position].meni)
                startActivity(intent)
            }
        })
    }
}