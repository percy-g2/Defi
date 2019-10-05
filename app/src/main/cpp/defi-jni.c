#include <jni.h>

JNIEXPORT jstring JNICALL
Java_com_androdevlinux_percy_defi_NativeUtils_getPocketbitsBaseUrl(JNIEnv *env,
                                                                          jclass type) {
    char * baseUrl = "https://pocketbits.in/";
    return (*env)->NewStringUTF(env, baseUrl);
}