package com.yibao.biggirl.model.girls;

import com.google.gson.Gson;

import java.util.List;

/**
 * 作者：Stran on 2017/3/29 07:25
 * 描述：${TODO}
 * 邮箱：strangermy@outlook.com
 */
public class GirlBean {

    /**
     * error : false
     * results : [{"_id":"58d49bad421aa93abf5d3b76","createdAt":"2017-03-24T12:08:13.590Z","desc":"3-24","publishedAt":"2017-03-24T12:12:34.753Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-03-24-17438359_1470934682925012_1066984844010979328_n.jpg","used":true,"who":"dmj"}]
     */

    private boolean           error;
    private List<ResultsBean> results;

    public static GirlBean objectFromData(String str) {

        return new Gson().fromJson(str, GirlBean.class);
    }

    public boolean isError() { return error;}

    public void setError(boolean error) { this.error = error;}

    public List<ResultsBean> getResults() { return results;}

    public void setResults(List<ResultsBean> results) { this.results = results;}


}
