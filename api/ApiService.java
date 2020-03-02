import com.scc.nd.attendance.api.model.BaseResponse;
import com.scc.nd.attendance.api.model.CheckPoint;
import com.scc.nd.attendance.api.model.CheckPointDetail;
import com.scc.nd.attendance.api.model.Login;
import com.scc.nd.attendance.api.model.Search;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import static com.scc.nd.attendance.api.ApiClient.END;

public interface ApiService {

    @FormUrlEncoded
    @POST(END)
    Observable<BaseResponse> signUp(@Field("action") String action,
                                    @Field("name") String name,
                                    @Field("email") String email,
                                    @Field("password") String password);

    @POST(END)
    Observable<BaseResponse> addCp(@Body RequestBody body);

}
