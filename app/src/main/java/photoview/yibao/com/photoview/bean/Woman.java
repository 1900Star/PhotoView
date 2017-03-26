package photoview.yibao.com.photoview.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：Stran on 2017/3/26 16:37
 * 描述：${TODO}
 * 邮箱：strangermy@outlook.com
 */
public class Woman implements Serializable{
    private List<ResultsBean> resultsBeen;

    public Woman(List<ResultsBean> resultsBeen) {
        this.resultsBeen = resultsBeen;
    }

    public List<ResultsBean> getResultsBeen() {
        return resultsBeen;
    }

    public void setResultsBeen(List<ResultsBean> resultsBeen) {
        this.resultsBeen = resultsBeen;
    }
}
