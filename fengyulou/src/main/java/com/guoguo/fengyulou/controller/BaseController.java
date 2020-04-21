package com.guoguo.fengyulou.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class BaseController {
    protected static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
}
