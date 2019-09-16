import br.com.cvaccari.moneytransfer.data.remote.vo.*
import io.reactivex.Observable
import retrofit2.http.*

interface API {

    @GET("/GenerateToken")
    fun generateToken(
        @Query("nome") name: String,
        @Query("email") email: String
    ): Observable<TokenResponseVO>

    @GET("/GetTransfers")
    fun getTransfers(
        @Query("token") token: String
    ): Observable<TransferResponseVO>

    @POST("/SendMoney")
    fun sendMoney(
        @Body sendMoneyRequest: SendMoneyRequestVO
    ): Observable<SendMoneyResponseVO>

    @GET("/GetUserContacts")
    fun getUserContacts(
        @Query("token") name: String
    ): Observable<UserContactsReponseVO>

}