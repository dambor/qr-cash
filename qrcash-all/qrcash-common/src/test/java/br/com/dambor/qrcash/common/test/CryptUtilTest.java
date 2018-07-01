package br.com.dambor.qrcash.common.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.dambor.qrcash.common.util.CryptUtil;

public class CryptUtilTest {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void test() {
		log.info(CryptUtil.encrypt("admin"));
	}
}
