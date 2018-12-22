LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_LDLIBS := -llog
LOCAL_MODULE := foo-prebuilt
LOCAL_SRC_FILES := libhttp_client.so
include $(PREBUILT_SHARED_LIBRARY)