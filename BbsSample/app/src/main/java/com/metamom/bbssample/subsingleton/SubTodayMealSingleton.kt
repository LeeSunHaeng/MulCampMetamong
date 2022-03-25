package com.metamom.bbssample.subsingleton

class SubTodayMealSingleton {

    /* #21# 로그인한 사용자의 구독 정보를 저장하기 위하여 사용 */
    companion object {
        var type :Int? = null
        var time :Int? = null

        override fun toString(): String {
            return "SubTodayMeal(type=$type, time=$time)"
        }
    }
}