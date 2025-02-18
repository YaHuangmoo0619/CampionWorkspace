package com.product_order.model;

import java.sql.Timestamp;
import java.util.List;

import com.product_order_details.model.Product_order_detailsVO;

public class Product_orderService {

	private Product_orderDAO_interface dao;

	public Product_orderService() {
		dao = new Product_orderDAO();
	}

	public Product_orderVO addProduct_order(Integer mbr_no, Integer prod_ord_stat,
			Integer prod_ord_sum, Integer used_pt, Integer ship_meth, Integer pay_meth, String ship_cty,
			String ship_dist, String ship_add, Integer receipt, String rmk, List<Product_order_detailsVO> list) {

		Product_orderVO product_orderVO = new Product_orderVO();

		product_orderVO.setMbr_no(mbr_no);
//		product_orderVO.setProd_ord_time(prod_ord_time);
		product_orderVO.setProd_ord_stat(prod_ord_stat);
		product_orderVO.setProd_ord_sum(prod_ord_sum);
		product_orderVO.setUsed_pt(used_pt);
		product_orderVO.setShip_meth(ship_meth);
		product_orderVO.setPay_meth(pay_meth);
		product_orderVO.setShip_cty(ship_cty);
		product_orderVO.setShip_dist(ship_dist);
		product_orderVO.setShip_add(ship_add);
		product_orderVO.setReceipt(receipt);
		product_orderVO.setRmk(rmk);
		product_orderVO = dao.insert(product_orderVO, list);

		return product_orderVO;
	}

	public Product_orderVO updateProduct_order(Integer prod_ord_no, Integer mbr_no, Timestamp prod_ord_time, Integer prod_ord_stat,
			Integer prod_ord_sum, Integer used_pt, Integer ship_meth, Integer pay_meth, String ship_cty,
			String ship_dist, String ship_add, Integer receipt, String rmk) {

		Product_orderVO product_orderVO = new Product_orderVO();

		product_orderVO.setProd_ord_no(prod_ord_no);
		product_orderVO.setMbr_no(mbr_no);
		product_orderVO.setProd_ord_time(prod_ord_time);
		product_orderVO.setProd_ord_stat(prod_ord_stat);
		product_orderVO.setProd_ord_sum(prod_ord_sum);
		product_orderVO.setUsed_pt(used_pt);
		product_orderVO.setShip_meth(ship_meth);
		product_orderVO.setPay_meth(pay_meth);
		product_orderVO.setShip_cty(ship_cty);
		product_orderVO.setShip_dist(ship_dist);
		product_orderVO.setShip_add(ship_add);
		product_orderVO.setReceipt(receipt);
		product_orderVO.setRmk(rmk);
		dao.update(product_orderVO);

		return product_orderVO;
	}

	public void deleteProduct_order(Integer prod_ord_no) {
		dao.delete(prod_ord_no);
	}

	public Product_orderVO getOneProduct_order(Integer prod_ord_no) {
		return dao.findByPrimaryKey(prod_ord_no);
	}

	public List<Product_orderVO> getByMbr(Integer mbr_no) {
		return dao.getByMbr(mbr_no);
	}
	
	public List<Product_orderVO> getAll() {
		return dao.getAll();
	}
	
	//���ĥ[��
	public void update_order_stat(Integer prod_ord_stat, Integer prod_ord_no) {
		dao.update_order_stat(prod_ord_stat, prod_ord_no);
	}
	//���ĥ[��
	public void cancel(Integer prod_ord_no) {
		dao.cancel(prod_ord_no);
	}
}