package com.roadtrack.onstar.VO;

public class ChunkObjectVO {
  private String chunk;
  
  private byte[] chunkBytes;
  
  private String chunkSize;
  
  private String fileID;
  
  private String offset;
  
  public String getChunk() {
    return this.chunk;
  }
  
  public byte[] getChunkBytes() {
    return this.chunkBytes;
  }
  
  public String getChunkSize() {
    return this.chunkSize;
  }
  
  public String getFileID() {
    return this.fileID;
  }
  
  public String getOffset() {
    return this.offset;
  }
  
  public void setChunk(byte[] paramArrayOfbyte) {
    this.chunkBytes = paramArrayOfbyte;
  }
  
  public void setChunkSize(String paramString) {
    this.chunkSize = paramString;
  }
  
  public void setFileID(String paramString) {
    this.fileID = paramString;
  }
  
  public void setOffset(String paramString) {
    this.offset = paramString;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/VO/ChunkObjectVO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */