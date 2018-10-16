package com.files.apps;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("/files")
public class FileUploadResource {
	private static final String FOLDER_PATH = "D:\\MyData\\Files";

	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.TEXT_PLAIN)
	public String uploadFile(@FormDataParam("file") InputStream fis,@FormDataParam("file") FormDataContentDisposition fdcd) {
		OutputStream outpuStream = null;
		String fileName = fdcd.getFileName();
		String filePath = FOLDER_PATH + fileName;
		try {
			int read = 0;  byte[] bytes = new byte[1024];
			outpuStream = new FileOutputStream(new File(filePath));
			while ((read = fis.read(bytes)) != -1) {
				outpuStream.write(bytes, 0, read);
			}
			outpuStream.flush(); outpuStream.close();
		} catch (IOException iox) {
			iox.printStackTrace();
		} finally {
			if (outpuStream != null) {
				try { outpuStream.close();
				} catch (Exception ex) {
				}
			}
		}
		return "File Uploaded Successfully !!";
	}
}

