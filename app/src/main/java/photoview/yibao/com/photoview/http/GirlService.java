package photoview.yibao.com.photoview.http;

import photoview.yibao.com.photoview.bean.GirlBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Author：Sid
 * Des：${TODO}
 * Time:2017/4/5 20:57
 */
public interface GirlService {
    @GET("api/data/{type}/{count}/{page}")
    Call<GirlBean> getGrils(@Path("type") String type,
                            @Path("count") int count,
                            @Path("page") int page);
}
