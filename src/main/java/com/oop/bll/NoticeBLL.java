package com.oop.bll;

import java.util.ArrayList;

import com.oop.model.Notice;
import com.oop.model.Ticket;
import com.oop.model.User;
import com.oop.repository.NoticeRepository;
import com.oop.repository.TicketRepository;

public class NoticeBLL {
	public void CreateNotice(Notice notice) {
		new NoticeRepository().CreateNotice(notice);	
	}
	
	public void EditNotice(Notice notice) {
		new NoticeRepository().EditNotice(notice);
	}
	
	public void DeleteNotice(int id) {
		new NoticeRepository().DeleteNotice(id);
	}
	
	public ArrayList<Notice> GetListOfNotices(){
		ArrayList<Notice> noticeList = new NoticeRepository().GetListOfNotices();
		return noticeList;
	}
}
