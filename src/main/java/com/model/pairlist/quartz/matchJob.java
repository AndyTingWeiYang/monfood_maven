package com.model.pairlist.quartz;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.model.pairlist.PairListVo;
import com.model.pairlist.dao.PairListDao;
import com.model.pairlist.dao.impl.PairListDaoImpl;
import com.model.user.dao.UserDAO;
import com.model.user.dao.impl.UserDAOImpl;

//實作Job介面
public class matchJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		PairListDao pairDao = new PairListDaoImpl();
		UserDAO userDao = new UserDAOImpl();
		
		// 取得所有會員ID
				List<Integer> listUserVO = userDao.getAllUserId();
				ArrayList<Integer> user = new ArrayList<>();
				for (Integer userVO1 : listUserVO) {
					int userId = userVO1;
					// 將結果放進user集合中，user集合為所有會員ID的集合
					user.add(userId);
				}

				System.out.println("所有會員ID" + user);

//				user[0]為準備要被配對的使用者ID 
				
				while (user.size() > 1) {
					
			//firstUser準備要配對的使用者集合
				ArrayList<Integer> firstUser = new ArrayList<>();
				firstUser.add(user.get(0));
				System.out.println("要被配對的A會員" + firstUser);
				
//				檢查pairList裡userid = user[0] (代表已經配對過) ，並個別放入集合內
//				pairedId及pairedId2集合
				List<PairListVo> pairedIdVo = pairDao.selectById(user.get(0));
				ArrayList<Integer> pairedId = new ArrayList<>();
				for(PairListVo alist: pairedIdVo) {
					int id = alist.getUserbId();
					pairedId.add(id);
				}

				List<PairListVo> pairedId2Vo = pairDao.selectById2(user.get(0));
				ArrayList<Integer> pairedId2 = new ArrayList<>();
				for(PairListVo alist: pairedId2Vo) {
					int id = alist.getUseraId();
					pairedId2.add(id);
				}
					
				// 取得pairedId及pairedId2聯集
				// pairedAll為所有已與user[0]配對過的會員名單
				List pairedAll = pairedId.parallelStream().collect(toList());
				List pairedAll2 = pairedId2.parallelStream().collect(toList());
				pairedAll.addAll(pairedAll2);

				System.out.println("已與A會員配對過的會員"+pairedAll);

				//取得user與pairedAll的差集
				List<Integer> reduce = user.stream().filter(item -> !pairedAll.contains(item)).collect(Collectors.toList());
		        
		        //再扣掉user[0]自己，差集結果為可放進user_b_id欄位的配對者(availblePaired)
		        List<Integer> availblePaired = reduce.stream().filter(item -> !firstUser.contains(item)).collect(Collectors.toList());
		        System.out.println("可與A會員配對的配對名單"+availblePaired);
		        
		        //toPairb為被配對到的B會員集合
				ArrayList<Integer> toPairb = new ArrayList<>();
				toPairb.add(availblePaired.get(0));
				System.out.println("被配對到的B會員" + toPairb);
		        
		        //幫user[0]與availblePaired[0]配對，並加入配對日期
		        PairListVo vo = new PairListVo();
				vo.setUseraId(user.get(0));
				vo.setUserbId(availblePaired.get(0));
				java.util.Date today = new java.util.Date();
				java.sql.Date pairedDate = new java.sql.Date(today.getTime());
				vo.setPairedDate(pairedDate);
		        pairDao.insert(vo);

		     
//		        從user集合裡將配對好的刪除
		        
		        //paired為配對好的userID聯集
				List paired = firstUser.parallelStream().collect(toList());
				List pairedb = toPairb.parallelStream().collect(toList());
				paired.addAll(pairedb);

				System.out.println("配對好的userID聯集"+paired);
				
				//將已將配對好的刪除
		         user = (ArrayList<Integer>) user.stream().filter(item -> !paired.contains(item)).collect(Collectors.toList());
		         System.out.println("當日未被配對過的會員" + user);
							         
		         	         
		         
				}
				System.out.println("觸發!");
				
	}



}
