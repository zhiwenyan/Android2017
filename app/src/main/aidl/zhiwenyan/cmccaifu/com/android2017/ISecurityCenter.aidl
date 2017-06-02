// ISecurityCenter.aidl
package zhiwenyan.cmccaifu.com.android2017;

// Declare any non-default types here with import statements

interface ISecurityCenter {
String encrypt(String content);
String decrypt(String password);
}
