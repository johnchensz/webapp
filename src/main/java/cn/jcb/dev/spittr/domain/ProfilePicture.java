package cn.jcb.dev.spittr.domain;

public class ProfilePicture {

	private String filename;
	private String originFilename;
	private long filesize;
	
	public ProfilePicture(){
		
	}
	
	public ProfilePicture(String filename,String originFilename, long filesize){
		this.filename = filename;
		this.originFilename = originFilename;
		this.filesize = filesize;
	}
	
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public long getFilesize() {
		return filesize;
	}
	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}
	public String getOriginFilename() {
		return originFilename;
	}
	public void setOriginFilename(String originFilename) {
		this.originFilename = originFilename;
	}
	
}
