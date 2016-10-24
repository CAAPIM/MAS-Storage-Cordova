/*
 * Copyright (c) 2016 CA, Inc.
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 *
 */

package com.ca.mas.cordova.storage;

import android.content.Context;
import android.util.Log;

import com.ca.mas.storage.MASSecureStorage;
import com.ca.mas.storage.MASStorage;

import com.ca.mas.foundation.MASCallback;
import com.ca.mas.foundation.MASConstants;

import org.apache.cordova.CallbackContext;
import org.json.JSONArray;

import java.util.Set;

/**
 * Created by trima09 on 10/13/2016.
 */

public class MASSecureStorageCommand {
    private static final String TAG = MASSecureStorageCommand.class.getCanonicalName();

    public static class SaveCommand extends Command {

        @Override
        public void execute(Context context, JSONArray args, final CallbackContext callbackContext) {
            try {
                MASStorage storage = new MASSecureStorage();
                String key = args.optString(1);
                Object data = args.opt(2);
                int segment_0 = args.optInt(3);
                int segment = segment_0 == 1 ? MASConstants.MAS_USER : (segment_0 == 2 ? MASConstants.MAS_APPLICATION : MASConstants.MAS_USER | MASConstants.MAS_APPLICATION);

                MASCallback<Void> callback = new MASCallback<Void>() {

                    @Override
                    public void onSuccess(Void result) {
                        success(callbackContext, true);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callbackContext.error(getError(e));
                    }
                };
                storage.save(key, data, segment, callback);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage(), e);
                callbackContext.error(getError(e));
            }
        }

        @Override
        public String getAction() {
            return "saveToCloud";
        }
    }

    public static class FindByKeyCommand extends Command {
        @Override
        public void execute(Context context, JSONArray args, final CallbackContext callbackContext) {
            try {
                MASStorage storage = new MASSecureStorage();
                String key = args.optString(0);
                int segment_0 = args.optInt(2);
                int segment = segment_0 == 1 ? MASConstants.MAS_USER : (segment_0 == 2 ? MASConstants.MAS_APPLICATION : MASConstants.MAS_USER | MASConstants.MAS_APPLICATION);
                MASCallback callback = new MASCallback() {

                    @Override
                    public void onSuccess(Object result) {
                        success(callbackContext, result);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callbackContext.error(getError(e));
                    }
                };
                storage.findByKey(key, segment, callback);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage(), e);
                callbackContext.error(getError(e));
            }
        }

        @Override
        public String getAction() {
            return "findByUsingKeyAndModeCloud";
        }
    }

    public static class DeleteCommand extends Command {
        @Override
        public void execute(Context context, JSONArray args, final CallbackContext callbackContext) {
            try {
                MASStorage storage = new MASSecureStorage();
                String key = args.optString(0);
                int segment_0 = args.optInt(2);
                int segment = segment_0 == 1 ? MASConstants.MAS_USER : (segment_0 == 2 ? MASConstants.MAS_APPLICATION : MASConstants.MAS_USER | MASConstants.MAS_APPLICATION);
                MASCallback<Void> callback = new MASCallback<Void>() {

                    @Override
                    public void onSuccess(Void result) {
                        success(callbackContext, true);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callbackContext.error(getError(e));
                    }
                };
                storage.delete(key, segment, callback);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage(), e);
                callbackContext.error(getError(e));
            }
        }

        @Override
        public String getAction() {
            return "deleteByUsingKeyAndModeCloud";
        }
    }

    public static class KeySetCommand extends Command {
        @Override
        public void execute(Context context, JSONArray args, final CallbackContext callbackContext) {
            try {
                MASStorage storage = new MASSecureStorage();
                int segment_0 = args.optInt(2);
                int segment = segment_0 == 1 ? MASConstants.MAS_USER : (segment_0 == 2 ? MASConstants.MAS_APPLICATION : MASConstants.MAS_USER | MASConstants.MAS_APPLICATION);
                MASCallback<Set<String>> callback = new MASCallback<Set<String>>() {

                    @Override
                    public void onSuccess(Set<String> result) {
                        success(callbackContext, result);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callbackContext.error(getError(e));
                    }
                };
                storage.keySet(segment, callback);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage(), e);
                callbackContext.error(getError(e));
            }
        }

        @Override
        public String getAction() {
            return "keySetForModeCloud";
        }
    }
}
