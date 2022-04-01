package com.metamom.bbssample.subscribe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.SkuDetails
import com.metamom.bbssample.R
import com.metamom.bbssample.databinding.ActivitySubPurchaseBinding

/* #21# 구글 인앱 결제 Activity */
// - 생성한 BillingModule을 생성하고, onBillingModuleIsReady()에서 상품정보를 불러와서 저장한다.
class SubPurchaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySubPurchaseBinding
    private lateinit var bm: BillingModule

    private var mSkuDetails = listOf<SkuDetails>()
        set(value) {
            field = value
            Log.d("SubPurchaseActivity", "#21# SubPurchaseActivity 결제 상품정보 가져오기 실행")
            setSkuDetailsView()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubPurchaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_sub_purchase)

        bm = BillingModule(this, lifecycleScope, object: BillingModule.Callback {
            override fun onBillingModulesIsReady() {
                bm.querySkuDetail(BillingClient.SkuType.INAPP, /*Sku.REMOVE_ADS,*/ Sku.TODAY_MEAL_1/*, Sku.TODAY_MEAL_3, Sku.TODAY_MEAL_5*/) { skuDetails ->
                    mSkuDetails = skuDetails
                }
            }

            override fun onSuccess(purchase: Purchase) {
                Log.d("SubPurchaseActivity", "#21# SubPurchaseActivity 구매 성공")
            }

            override fun onFailure(errorCode: Int) {
                when (errorCode) {
                    BillingClient.BillingResponseCode.ITEM_ALREADY_OWNED -> {
                        Toast.makeText(this@SubPurchaseActivity, "이미 구입한 상품입니다.", Toast.LENGTH_LONG).show()
                    }
                    BillingClient.BillingResponseCode.USER_CANCELED -> {
                        Toast.makeText(this@SubPurchaseActivity, "구매를 취소하였습니다.", Toast.LENGTH_LONG).show()
                    }
                    else -> {
                        Log.d("SubPurchaseActivity", "#21# SubPurchaseActivity 구매 Error 발생 > $errorCode")
                    }
                }
            }
        })
        setClickListeners()
    }

    fun setClickListeners() {
        with (binding) {
            subPurchaseBtn.setOnClickListener {
                mSkuDetails.find { it.sku == Sku.TODAY_MEAL_1 }?.let { skuDetails ->
                    bm.purchase(skuDetails)
                } ?: also {
                    Toast.makeText(this@SubPurchaseActivity, "상품을 찾을 수 없습니다.", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    /* 상품정보 표시 */
    // - getPrice _화폐기호를 포함한 포맷으로 지정된 가격을 반환
    // - getPriceAmountMicros _백만분의 일(micro) 단위의 가격을 반환, Long 형식 | ex) $7.99 > 7990000
    // - getPriceCurrencyCode _화폐코드 반환 | ex) "USD", "KRW"
    // - getSku: 고유 아이디인 SKU 코드 반환 | 구글 플레이 콘솔에서 생성하였던 상품 ID > "today_meal_1", "today_meal_3", "today_meal_5"
    fun setSkuDetailsView() {
        val builder = StringBuilder()
        for (skuDetail in mSkuDetails) {
            builder.append("<${skuDetail.title}\n")
            builder.append(skuDetail.price)
            builder.append("\n=========================\n\n")
        }
        binding.subPurchaseTvSkuTxt.text = builder
    }

    override fun onResume() {
        super.onResume()
        bm.onResume(BillingClient.SkuType.INAPP)
    }

    object Sku {
        const val TODAY_MEAL_1 = "today_meal_1"
        /*const val TODAY_MEAL_3 = "today_meal_3"
        const val TODAY_MEAL_5 = "today_meal_5"*/
    }


}