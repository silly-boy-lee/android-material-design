package com.zhu.material_design.common.util.network.okhttp.bean;


import com.zhu.material_design.common.util.network.okhttp.callback.ProgressCallback;

/**
 * 上传/下载进度回调信息体
 * @author zhousf
 */
public class ProgressMessage extends OkMessage{

    public ProgressCallback progressCallback;
    public int percent;
    public long bytesWritten;
    public long contentLength;
    public boolean done;

    public ProgressMessage(int what, ProgressCallback progressCallback, int percent, long bytesWritten, long contentLength, boolean done) {
        this.what = what;
        this.percent = percent;
        this.bytesWritten = bytesWritten;
        this.contentLength = contentLength;
        this.done = done;
        this.progressCallback = progressCallback;
    }



}
