package androidx.core.graphics;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import android.os.StrictMode;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class TypefaceCompatUtil {
  public static void closeQuietly(Closeable paramCloseable) {
    if (paramCloseable != null)
      try {
        paramCloseable.close();
      } catch (IOException iOException) {} 
  }
  
  public static ByteBuffer copyToDirectBuffer(Context paramContext, Resources paramResources, int paramInt) {
    File file = getTempFile(paramContext);
    if (file == null)
      return null; 
    try {
      boolean bool = copyToFile(file, paramResources, paramInt);
      if (!bool)
        return null; 
      return mmap(file);
    } finally {
      file.delete();
    } 
  }
  
  public static boolean copyToFile(File paramFile, Resources paramResources, int paramInt) {
    try {
      InputStream inputStream = paramResources.openRawResource(paramInt);
    } finally {
      paramFile = null;
    } 
    closeQuietly((Closeable)paramResources);
    throw paramFile;
  }
  
  public static boolean copyToFile(File paramFile, InputStream paramInputStream) {
    StrictMode.ThreadPolicy threadPolicy = StrictMode.allowThreadDiskWrites();
    File file2 = null;
    FileOutputStream fileOutputStream2 = null;
    FileOutputStream fileOutputStream1 = fileOutputStream2;
    try {
      FileOutputStream fileOutputStream = new FileOutputStream();
      fileOutputStream1 = fileOutputStream2;
      this(paramFile, false);
      try {
        byte[] arrayOfByte = new byte[1024];
        while (true) {
          int i = paramInputStream.read(arrayOfByte);
          if (i != -1) {
            fileOutputStream.write(arrayOfByte, 0, i);
            continue;
          } 
          closeQuietly(fileOutputStream);
          return true;
        } 
      } catch (IOException null) {
      
      } finally {
        paramFile = null;
      } 
    } catch (IOException iOException) {
      paramFile = file2;
    } finally {}
    File file1 = paramFile;
    StringBuilder stringBuilder = new StringBuilder();
    file1 = paramFile;
    this();
    file1 = paramFile;
    stringBuilder.append("Error copying resource contents to temp file: ");
    file1 = paramFile;
    stringBuilder.append(iOException.getMessage());
    file1 = paramFile;
    stringBuilder.toString();
    closeQuietly((Closeable)paramFile);
    StrictMode.setThreadPolicy(threadPolicy);
    return false;
  }
  
  public static File getTempFile(Context paramContext) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(".font");
    stringBuilder.append(Process.myPid());
    stringBuilder.append("-");
    stringBuilder.append(Process.myTid());
    stringBuilder.append("-");
    String str = stringBuilder.toString();
    byte b = 0;
    while (true) {
      if (b < 100) {
        File file2 = paramContext.getCacheDir();
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append(str);
        stringBuilder1.append(b);
        File file1 = new File(file2, stringBuilder1.toString());
        try {
          boolean bool = file1.createNewFile();
          if (bool)
            return file1; 
        } catch (IOException iOException) {}
        b++;
        continue;
      } 
      return null;
    } 
  }
  
  public static ByteBuffer mmap(Context paramContext, CancellationSignal paramCancellationSignal, Uri paramUri) {
    ContentResolver contentResolver = paramContext.getContentResolver();
    try {
      ParcelFileDescriptor parcelFileDescriptor = contentResolver.openFileDescriptor(paramUri, "r", paramCancellationSignal);
      if (parcelFileDescriptor == null) {
        if (parcelFileDescriptor != null)
          parcelFileDescriptor.close(); 
        return null;
      } 
      try {
        FileInputStream fileInputStream = new FileInputStream();
        this(parcelFileDescriptor.getFileDescriptor());
      } finally {
        paramCancellationSignal = null;
      } 
    } catch (IOException iOException) {
      return null;
    } 
  }
  
  private static ByteBuffer mmap(File paramFile) {
    try {
      FileInputStream fileInputStream = new FileInputStream();
      this(paramFile);
      try {
        FileChannel fileChannel = fileInputStream.getChannel();
        long l = fileChannel.size();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, 0L, l);
      } finally {
        paramFile = null;
      } 
    } catch (IOException iOException) {
      return null;
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/core/graphics/TypefaceCompatUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */