package com.cl.action;

import java.util.ArrayList;

import org.apache.struts2.ServletActionContext;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import com.cl.dao.LearningStatus;
import com.cl.entity.Pie;
import com.cl.entity.PiePart;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ViewChartAction extends ActionSupport {
	private JFreeChart chart;

	public JFreeChart getChart() {
		ActionContext act = ActionContext.getContext();
		String piename = ServletActionContext.getRequest().getParameter("pilename");
		ArrayList<LearningStatus> ls = (ArrayList<LearningStatus>)act.getSession().get("learningstatus");
		Pie pie = LearningStatus.getPieByPieName(piename, ls);
		if (pie != null) {
			chart = ChartFactory.createPieChart(pie.getPiename(), getDataSet(pie), true, true, false);
		}
		return chart;
	}

	public void setChart(JFreeChart chart) {
		this.chart = chart;
	}
	
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	private DefaultPieDataset getDataSet(Pie pie) {
		DefaultPieDataset dpd = new DefaultPieDataset();
		ArrayList<PiePart> part = pie.getParts();
		for (int i = 0; i < part.size(); i++) {
			if (part.get(i).getPartnumb() != 0)
				dpd.setValue(part.get(i).getPartname(), part.get(i).getPartnumb());
		}
		return dpd;
	}
}
