package cn.com.alo7.inf.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.com.alo7.inf.AloApplicationStarterTests;
import cn.com.alo7.inf.common.utils.PageUtils;
import cn.com.alo7.inf.entity.WorkFullView;

public class WorkViewServiceTest extends AloApplicationStarterTests {

	
	@Autowired
	private IWorkViewService workViewService;
	@Test
	public void testpage() {
		
		Pageable pageable = PageUtils.build(0, 15);
		Page<WorkFullView> page = this.workViewService.findWorkByAlbumId(2L, pageable);
		System.out.println(page.getContent().size());
		System.out.println("-------------------------");
		for (WorkFullView workFullView : page) {
			System.out.println(workFullView.getTitle());
		}
		System.out.println("-------------------------");
		page.forEach(e -> System.out.println(e));
	}

}
