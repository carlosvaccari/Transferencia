package br.com.cvaccari.moneytransfer.data.remote

import br.com.cvaccari.moneytransfer.BuildConfig.*
import okhttp3.*
import okhttp3.ResponseBody.*

class MockInterceptor : Interceptor {

    companion object {
        const val SUCCESS_CODE = 200
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        if (DEBUG) {
            val uri = chain.request().url().uri().toString()
            val responseString = when {
                uri.contains("GenerateToken") -> getToken
                uri.contains("GetTransfers") -> getTransfers
                uri.contains("SendMoney") -> getSentMoney
                uri.contains("GetUserContacts") -> getUserContacts
                else -> ""
            }

            return chain.proceed(chain.request())
                .newBuilder()
                .code(SUCCESS_CODE)
                .protocol(Protocol.HTTP_2)
                .message(responseString)
                .body(
                    create(
                        MediaType.parse("application/json"),
                        responseString.toByteArray()
                    )
                )
                .addHeader("content-type", "application/json")
                .build()
        } else {
            //just to be on safe side.
            throw IllegalAccessError(
                "MockInterceptor is only meant for Testing Purposes and " +
                        "bound to be used only with DEBUG mode"
            )
        }
    }

}


const val getToken = """
{
	"token": "1d40d305-c836-43a2-b4db-acc56bcc1393"
}
"""


const val getSentMoney = """
{
	"result": true
}
"""

const val getTransfers = """
{"transferList":[
{
    "Id": 0,
    "name": "Anderson Santos",
    "phone": 11983684628,
    "amount": 30000,
    "ClienteId": 0,
    "Valor": 24,
    "Token": "1d40d305-c836-43a2-b4db-acc56bcc1393",
    "Data": "2016-08-02T14:25:37.55",
        "photo": "user_0"
},
{
    "Id": 1,
    "name": "Bianca Gente Fina",
    "phone": 11983684628,
    "amount": 5000,
    "ClienteId": 1,
    "Valor": 24,
    "Token": "1d40d305-c836-43a2-b4db-acc56bcc1393",
    "Data": "2016-08-02T14:25:37.55",
    "photo": "user_1"
},
{
    "Id": 2,
    "name": "Bianca Gente Fina",
    "phone": 11983684628,
    "amount": 5000,
    "ClienteId": 1,
    "Valor": 24,
    "Token": "1d40d305-c836-43a2-b4db-acc56bcc1393",
    "Data": "2016-08-02T14:25:37.55",
    "photo": "user_1"
},
{
    "Id": 3,
    "name": "Bianca Gente Fina",
    "phone": 11983684628,
    "amount": 5000,
    "ClienteId": 1,
    "Valor": 24,
    "Token": "1d40d305-c836-43a2-b4db-acc56bcc1393",
    "Data": "2016-08-02T14:25:37.55",
    "photo": "user_1"

},
{
    "Id": 4,
    "name": "Bianca Gente Fina",
    "phone": 11983684628,
    "amount": 5000,
    "ClienteId": 1,
    "Valor": 24,
    "Token": "1d40d305-c836-43a2-b4db-acc56bcc1393",
    "Data": "2016-08-02T14:25:37.55",
    "photo": "user_1"
},
{
    "Id": 5,
    "name": "Débora Pomposa",
    "phone": 11983684628,
    "amount": 10000,
    "ClienteId": 2,
    "Valor": 24,
    "Token": "1d40d305-c836-43a2-b4db-acc56bcc1393",
    "Data": "2016-08-02T14:25:37.55"


},
{
    "Id": 6,
    "name": "Débora Pomposa",
    "phone": 11983684628,
    "amount": 1000,
    "ClienteId": 2,
    "Valor": 24,
    "Token": "1d40d305-c836-43a2-b4db-acc56bcc1393",
    "Data": "2016-08-02T14:25:37.55"

},
{
    "Id": 7,
    "name": "Sarah Mascarenhas",
    "phone": 11983684628,
    "amount": 2000,
    "ClienteId": 8,
    "Valor": 24,
    "Token": "1d40d305-c836-43a2-b4db-acc56bcc1393",
    "Data": "2016-08-02T14:25:37.55",
    "photo": "user_4"
},
{
    "Id": 8,
    "name": "Sarah Mascarenhas",
    "phone": 11983684628,
    "amount": 3000,
    "ClienteId": 8,
    "Valor": 24,
    "Token": "1d40d305-c836-43a2-b4db-acc56bcc1393",
    "Data": "2016-08-02T14:25:37.55",
    "photo": "user_4"
},
{
    "Id": 9,
    "name": "Sarah Mascarenhas",
    "phone": 11983684628,
    "amount": 5300,
    "ClienteId": 8,
    "Valor": 24,
    "Token": "1d40d305-c836-43a2-b4db-acc56bcc1393",
    "Data": "2016-08-02T14:25:37.55",
    "photo": "user_4"
},
{
    "Id": 10,
    "name": "Rita Vaccari",
    "phone": 11983684628,
    "amount": 9800,
    "ClienteId": 9,
    "Valor": 24,
    "Token": "1d40d305-c836-43a2-b4db-acc56bcc1393",
    "Data": "2016-08-02T14:25:37.55",
    "photo": "user_8"
},
{
    "Id": 11,
    "name": "Rita Vaccari",
    "phone": 11983684628,
    "amount": 20000,
    "ClienteId": 9,
    "Valor": 24,
    "Token": "1d40d305-c836-43a2-b4db-acc56bcc1393",
    "Data": "2016-08-02T14:25:37.55",
    "photo": "user_8"
},
{
    "Id": 12,
    "name": "Rita Vaccari",
    "phone": 11983684628,
    "amount": 4500,
    "ClienteId": 9,
    "Valor": 24,
    "Token": "1d40d305-c836-43a2-b4db-acc56bcc1393",
    "Data": "2016-08-02T14:25:37.55",
    "photo": "user_8"
},
{
    "Id": 13,
    "name": "Amanda Gama",
    "phone": 11983684628,
    "amount": 12700,
    "ClienteId": 10,
    "Valor": 24,
    "Token": "1d40d305-c836-43a2-b4db-acc56bcc1393",
    "Data": "2016-08-02T14:25:37.55",
    "photo": "user_9"
}
]
}
"""

const val getUserContacts = """
{"contacts":[
{
    "Id": 0,
    "name": "Anderson Santos",
    "phone": 11983684628,
    "photo": "user_0"
},
{
    "Id": 1,
    "name": "Bianca Gente Fina",
    "phone": 11983684628,
    "photo": "user_1"
},
{
    "Id": 2,
    "name": "Débora Pomposa",
    "phone": 11983684628
},
{
    "Id": 3,
    "name": "Darlene da Terra",
    "phone": 11983684628,
    "photo": "user_3"
},
{
    "Id": 4,
    "name": "Whinderson Nunes",
    "phone": 11983684628,
    "photo": "user_5"
},
{
    "Id": 5,
    "name": "Fabiana Casca Grossa da Santa Maria de Lourdes",
    "phone": 11983684628,
    "photo": "user_6"
},
{
    "Id": 6,
    "name": "Francisca Sabida",
    "phone": 11983684628,
    "photo": "user_7"
},
{
    "Id": 7,
    "name": "Gerson Mariano",
    "phone": 11983684628,
    "photo": "user_10"
},
{
    "Id": 8,
    "name": "Sarah Mascarenhas",
    "phone": 11983684628,
    "photo": "user_4"
},
{
    "Id": 9,
    "name": "Rita Vaccari",
    "phone": 11983684628,
    "photo": "user_8"
},
{
    "Id": 10,
    "name": "Amanda Gama",
    "phone": 11983684628,
    "photo": "user_9"
},
{
    "Id": 11,
    "name": "Carlos Gama",
    "phone": 11983684628,
    "photo": "user_11"
},
{
    "Id": 12,
    "name": "Carlos Vaccari",
    "phone": 11983684628,
    "photo": "user_12"
},
{
    "Id": 13,
    "name": "Thiago Ventura",
    "phone": 11983684628,
    "photo": "user_13"
}
]
}
"""
