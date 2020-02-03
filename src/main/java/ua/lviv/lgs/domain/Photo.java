package ua.lviv.lgs.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "photo")
public class Photo {

	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@JsonIgnore
	@Column(name = "size")
	private long fileSize;

	@Column(name = "name")
	private String fileName;

	@JsonIgnore
	@Column(name = "status")
	private String uploadStatus;

	@Lob
	@Column(name = "content")
	private byte[] content;

	public Photo() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getUploadStatus() {
		return uploadStatus;
	}

	public void setUploadStatus(String uploadStatus) {
		this.uploadStatus = uploadStatus;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

}
