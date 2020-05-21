package com.wey.ten.era.rbac.system.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.google.code.kaptcha.impl.DefaultKaptcha;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PictureService {

	@Autowired
	private DefaultKaptcha defaultKaptcha;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	public String createPicVerificationCode(String param, HttpServletRequest request) {
		if (StringUtils.isNotBlank(param)) {
			byte[] captchaChallengeAsJpeg;
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			try {
				String verificationCodeText = defaultKaptcha.createText();
				// 加入到session方便后面验证
				stringRedisTemplate.opsForValue().set(param, verificationCodeText);
				BufferedImage image = defaultKaptcha.createImage(verificationCodeText);
				ImageIO.write(image, "jpg", outputStream);
				captchaChallengeAsJpeg = outputStream.toByteArray();
				return Base64.getEncoder().encodeToString(captchaChallengeAsJpeg);
			} catch (Exception e) {
				log.error("生成图片验证码异常" + e);
				e.printStackTrace();
			} finally {
				try {
					outputStream.close();
				} catch (IOException e) {
					log.error("生成图片验证码结束，关闭ByteArrayOutputStream资源失败" + e);
					e.printStackTrace();
				}
			}
		}
		return "验证码生成失败，请重试";
	}
}
