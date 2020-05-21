package com.wey.ten.era.gateway.auth;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import com.alibaba.fastjson.JSON;
import com.wey.ten.era.common.ResponseResult;
import com.wey.ten.era.common.exception.TokenAuthenticationException;
import com.wey.ten.era.common.utils.ErrorCode;
import com.wey.ten.era.common.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//@Slf4j
//@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {

	@Value("${secretKey:123456}")
	private String secretKey;

	// @Autowired
	// private StringRedisTemplate stringRedisTemplate;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		ServerHttpRequest serverHttpRequest = exchange.getRequest();
		ServerHttpResponse serverHttpResponse = exchange.getResponse();
		String uri = serverHttpRequest.getURI().getPath();

		// 检查白名单（配置）
		if (uri.indexOf("/user/oauth") >= 0) {
			return chain.filter(exchange);
		}

//		String token = serverHttpRequest.getHeaders().getFirst("token");
//		if (StringUtils.isBlank(token)) {
//			serverHttpResponse.setStatusCode(HttpStatus.UNAUTHORIZED);
//			return getVoidMono(serverHttpResponse, ErrorCode.TOKEN_MISSION);
//		}

		// todo 检查Redis中是否有此Token

//		try {
//			JWTUtil.verifyToken(token, secretKey);
//		} catch (TokenAuthenticationException ex) {
//			return getVoidMono(serverHttpResponse, ErrorCode.TOKEN_INVALID);
//		} catch (Exception ex) {
//			return getVoidMono(serverHttpResponse, ErrorCode.UNKNOWN_ERROR);
//		}

		//String userId = JWTUtil.getUserInfo(token);

		ServerHttpRequest mutableReq = serverHttpRequest.mutate().header("userId", "test").build();
		ServerWebExchange mutableExchange = exchange.mutate().request(mutableReq).build();

		return chain.filter(mutableExchange);
	}

	private Mono<Void> getVoidMono(ServerHttpResponse serverHttpResponse, ErrorCode errorCode) {
		serverHttpResponse.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
		ResponseResult responseResult = ResponseResult.error(Integer.valueOf(errorCode.getValue()), errorCode.getDesc());
		DataBuffer dataBuffer = serverHttpResponse.bufferFactory().wrap(JSON.toJSONString(responseResult).getBytes());
		return serverHttpResponse.writeWith(Flux.just(dataBuffer));
	}

	@Override
	public int getOrder() {
		return -100;
	}
}