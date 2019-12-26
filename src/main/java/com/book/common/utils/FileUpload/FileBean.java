package com.book.common.utils.FileUpload;

import java.util.Arrays;

public class FileBean {
    private String errno;
    private String[] data;

    @Override
    public String toString() {
        return "FileBean{" +
                "errno='" + errno + '\'' +
                ", data=" + Arrays.toString(data) +
                '}';
    }

    public String getErrno() {
        return errno;
    }

    public void setErrno(String errno) {
        this.errno = errno;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }

    public FileBean() {
    }

    public FileBean(String errno, String[] data) {
        this.errno = errno;
        this.data = data;
    }
}
