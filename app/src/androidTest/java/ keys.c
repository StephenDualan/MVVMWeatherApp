#include <jni.h>

JNIEXPORT jstring JNICALL
Java_com_denbondd_justweather_services_Keys_getOWMkey(JNIEnv* env, jobject this) {
    return (*env)->NewStringUTF(env, "ee046eed125e63ab2f41f78533442024");
}