package himedia.dao;

import java.util.List;

public interface GuestbookDao {

	public List <GuestbookVo> getList();
	
	public boolean add(GuestbookVo vo);
	
	public boolean del(Long no, String password);
	
	public GuestbookVo search(Long no, String password);
	
	public boolean update(Long no, String content);
}
