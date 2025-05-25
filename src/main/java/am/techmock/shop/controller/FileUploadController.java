package am.techmock.shop.controller;

import am.techmock.sbwp.util.Tuple;
import am.techmock.sbwp.util.Tuple2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/file")
public class FileUploadController {

	private static final Pattern FILE_NAME_PATTERN = Pattern.compile("(.*)(\\.[^.]+)$");

	@Value("${upload.directory}")
	private String uploadDirectory;

	@GetMapping("/size")
	public Map<String, Object> filesize(@RequestParam String filename) {

		var file = new File(uploadDirectory + filename);
		return Map.of("size", file.exists() && file.isFile() ? file.length() : 0);
	}

	@PostMapping("/upload")
	public Map<String, String> upload(@RequestParam("file") MultipartFile file) throws IOException {

		var fileName = fileNameAndExtension(file);

		String result = "media/" + fileName.first() + "--" + UUID.randomUUID() + fileName.second();
		file.transferTo(new File(uploadDirectory + result));

		return Map.of("filename", result);
	}

	private static Tuple2<String, String> fileNameAndExtension(MultipartFile file) {

		String filename = file.getOriginalFilename();
		var matcher = FILE_NAME_PATTERN.matcher(filename);

		return matcher.matches() ? Tuple.of(matcher.group(1), matcher.group(2)) :  Tuple.of("", "");
	}
}
