package hr.tvz.android_menza

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.math.RoundingMode

class OdabranaMenzaActivity : AppCompatActivity() {
    private val nazivText: TextView
        get() = findViewById(R.id.nazivView)
    private val adresaText: TextView
        get() = findViewById(R.id.adresaView)
    private val radnoVrijemeText: TextView
        get() = findViewById(R.id.radnoVrijemeView)
    private val meniText: TextView
        get() = findViewById(R.id.meniView)
    private val infoText: TextView
        get() = findViewById(R.id.infoView)
    private val posjecenostGumb: Button
        get() = findViewById(R.id.posjGumb)
    private lateinit var client : RestService
    private var tokenRefresher: TokenRefresher = TokenRefresher()
    private val posjecenostText: TextView
        get() = findViewById(R.id.posjecenostView)
    private val guzvaText: TextView
        get() = findViewById(R.id.guzvaView)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_odabrana_menza)

        val bundle: Bundle? = intent.extras
        val naziv = bundle!!.getString("nazivMenze")
        val adresa = bundle.getString("adresaMenze")
        val radnoVrijeme = bundle.getString("radnoVrijemeMenze")
        val meni = bundle.getString("meniMenze")
        val info = bundle.getString("infoMenze")

        nazivText.text = naziv
        adresaText.text = adresa
        radnoVrijemeText.text = radnoVrijeme
        meniText.text = meni
        infoText.text = info
        client = APIService().getService(RestService::class.java)
        tokenRefresher.checkAndRefreshToken(client)
        client = APIService().getServiceWithToken(RestService::class.java, Token.accessToken.toString())
        println("s")
        println(naziv.toString())

        var guzva: Call<Int> = client.getGuzva(naziv.toString())
        guzva.enqueue(object: Callback<Int>{
            override fun onResponse(call: Call<Int>, response: Response<Int>) {
                if(response.isSuccessful)
                {
                    println("ugaugnerugnrugna" + response.body())
                    if(response.body() == 1)
                        guzvaText.text = "Trenutno je guzva!"
                    else if(response.body() == 0)
                        guzvaText.text = ""
                    else
                        guzvaText.text = "GRESKAAAA!"
                }
                else
                {
                    guzvaText.text = "GRESKA!"
                    println("GHURHGURHGIR" + response.message())
                }
            }

            override fun onFailure(call: Call<Int>, t: Throwable) {
                print("IGHNJININGIJDNIGJNRGHERUHGURHGDJKSN")
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }
        })
        posjecenostGumb.setOnClickListener {
            var posjecenost: Call<Double> = client.getPosjecenost(naziv.toString())
            posjecenost.enqueue(object: Callback<Double>{
                override fun onResponse(call: Call<Double>, response: Response<Double>) {
                    if(response.body()!! >= 0.0)
                        posjecenostText.text = response.body()!!
                            .toBigDecimal().setScale(2, RoundingMode.UP).toString()
                    else
                        posjecenostText.text = "Greska!"
                }

                override fun onFailure(call: Call<Double>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                }
            })
        }
    }
}