package com.model2.mvc.service.product.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;


@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:config/commonservice.xml" })
@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
		"classpath:config/context-aspect.xml",
		"classpath:config/context-mybatis.xml",
		"classpath:config/context-transaction.xml" })
public class ProductServiceTest {


		//==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
		@Autowired
		@Qualifier("productServiceImpl")
		private ProductService productService;

		//@Test
		public void testAddUser() throws Exception {
			
			Product product = new Product();
			
			product.setProdName("한성컴퓨터");
			product.setProdDetail("빨리빨리");
			product.setManuDate("20180519");
			product.setPrice(500000);
			product.setFileName("이미지파일");
			
			
			productService.addProduct(product);
			
			product = productService.getProduct(10030);
			
			Assert.assertEquals("컴퓨터", product.getProdName());
			
		}
		
		//@Test
		public void testGetProduct() throws Exception {
			
			Product product = new Product();

			
			product = productService.getProduct(10030);
			System.out.println("product :" +product);
			
			Assert.assertEquals("컴퓨터", product.getProdName());
			Assert.assertEquals("빨리", product.getProdDetail());
			Assert.assertEquals("20180119", product.getManuDate());
			Assert.assertEquals(300000, product.getPrice());
			Assert.assertEquals("이미지파일", product.getFileName());
	
		}
		
		//@Test
		 public void testUpdateProduct() throws Exception{
			 
			Product product = productService.getProduct(10030);
					
			Assert.assertNotNull(product);
			
			Assert.assertEquals("삼성컴퓨터", product.getProdName());
			Assert.assertEquals("빨리", product.getProdDetail());
			Assert.assertEquals("20180119", product.getManuDate());
			Assert.assertEquals(400000, product.getPrice());
			Assert.assertEquals("이미지파일", product.getFileName());

			product.setProdName("슈퍼컴퓨터");
			product.setPrice(600000);
			product.setManuDate("20181031");
		
			productService.updateProduct(product);
			
			product = productService.getProduct(10030);
			Assert.assertNotNull(product);
			
			
			

				
			//==> API 확인
			Assert.assertEquals("삼성컴퓨터", product.getProdName());
			Assert.assertEquals(400000, product.getPrice());
			Assert.assertEquals("20181031", product.getManuDate());
		
		 }
		//@Test
		 public void testGetProductListAll() throws Exception{
			 
			 	Search search = new Search();
			 	/*
			 	search.setCurrentPage(1);
			 	search.setPageSize(3);
			 	Map<String,Object> map = productService.getProductList(search);
			 	
			 	List<Object> list = (List<Object>)map.get("list");
			 	Assert.assertEquals(3, list.size());
			 	
				
			 	System.out.println(list);
			 	
			 	Integer totalCount = (Integer)map.get("totalCount");
			 	System.out.println(totalCount);
			 	*/
			 	
			 	System.out.println("=======================================");
			 	
			 	search.setCurrentPage(1);
			 	search.setPageSize(3);
			 	search.setSearchCondition("1");
			 	search.setSearchKeyword("%컴퓨터");
			 	
			 	Map<String,Object> map = productService.getProductList(search);
			 	
			 	List<Object> list = (List<Object>)map.get("list");
			 	
			 	Assert.assertEquals(2, list.size());
			 	
			 	
			 	System.out.println(list);
			 	
			 	Integer totalCount = (Integer)map.get("totalCount");
			 	System.out.println(totalCount);
			 }
		 //@Test
		 public void testGetProductListByProductNo() throws Exception{
			 
		 	Search search = new Search();
		 	search.setCurrentPage(1);
		 	search.setPageSize(3);
		 	search.setSearchCondition("0");
		 	search.setSearchKeyword("10030");
		 	Map<String,Object> map = productService.getProductList(search);
		 	
		 	List<Object> list = (List<Object>)map.get("list");
		 	Assert.assertEquals(1, list.size());
		 	
			//==> console 확인
		 	System.out.println(list);
		 	
		 	Integer totalCount = (Integer)map.get("totalCount");
		 	System.out.println(totalCount);
		 	
		 	System.out.println("=======================================");
		 	
		 	search.setSearchCondition("0");
		 	search.setSearchKeyword(""+System.currentTimeMillis());
		 	map = productService.getProductList(search);
		 	
		 	list = (List<Object>)map.get("list");
		 	Assert.assertEquals(0, list.size());
		 	
			//==> console 확인
		 	//System.out.println(list);
		 	
		 	totalCount = (Integer)map.get("totalCount");
		 	System.out.println(totalCount);
		 }
		 
		 @Test
		 public void testGetProductListByProductName() throws Exception{
			 
		 	Search search = new Search();
		 	search.setCurrentPage(1);
		 	search.setPageSize(3);
		 	search.setSearchCondition("1");
		 	search.setSearchKeyword("%컴퓨터");
		 	Map<String,Object> map = productService.getProductList(search);
		 	
		 	List<Object> list = (List<Object>)map.get("list");
		 	Assert.assertEquals(1, list.size());
		 	
			//==> console 확인
		 	//System.out.println(list);
		 	
		 	Integer totalCount = (Integer)map.get("totalCount");
		 	System.out.println(totalCount);
		 	
		 	System.out.println("=======================================");
		 	
		 	search.setSearchCondition("1");
		 	search.setSearchKeyword(""+System.currentTimeMillis());
		 	map = productService.getProductList(search);
		 	
		 	list = (List<Object>)map.get("list");
		 	Assert.assertEquals(0, list.size());
		 	
			//==> console 확인
		 	//System.out.println(list);
		 	
		 	totalCount = (Integer)map.get("totalCount");
		 	System.out.println(totalCount);
		 }	 
		
		 //@Test
		 public void testGetProductListByProductPrice() throws Exception{
			 
		 	Search search = new Search();
		 	search.setCurrentPage(1);
		 	search.setPageSize(3);
		 	search.setSearchCondition("2");
		 	search.setSearchKeyword("10000");
		 	Map<String,Object> map = productService.getProductList(search);
		 	
		 	List<Object> list = (List<Object>)map.get("list");
		 	Assert.assertEquals(3, list.size());
		 	
			//==> console 확인
		 	//System.out.println(list);
		 	
		 	Integer totalCount = (Integer)map.get("totalCount");
		 	System.out.println(totalCount);
		 	
		 	System.out.println("=======================================");
		 	
		 	search.setSearchCondition("1");
		 	search.setSearchKeyword(""+System.currentTimeMillis());
		 	map = productService.getProductList(search);
		 	
		 	list = (List<Object>)map.get("list");
		 	Assert.assertEquals(0, list.size());
		 	
			//==> console 확인
		 	//System.out.println(list);
		 	
		 	totalCount = (Integer)map.get("totalCount");
		 	System.out.println(totalCount);
		 }	 
		
	
}
