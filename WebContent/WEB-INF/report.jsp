<!DOCTYPE html>
<%@ page import="com.check.WebConfig"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="com.check.pojo.Structrue"%>
<%@ page import="com.check.constant.VarKey"%>
<%@ page import="com.check.pojo.Dict"%>
<%@ page import="java.util.List"%>
<%@	page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%
	SimpleDateFormat titleSDF = new SimpleDateFormat("_yyyy_MM_dd");
	String titleDate = titleSDF.format(new Date());
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${ejwContextPath}/script/jquery-1.5.js"></script>
<title><%=WebConfig.PROJECTNAME + WebConfig.TITLE + titleDate%></title>
<style type="text/css">
table {
	margin-left: 2%;
	margin-right: 2%;
	width: 96%;
	border-collapse: collapse;
}

td {
	height: 25px;
	padding: 5px;
}

.td-height {
	height: 35px;
	padding: 5px;
}

.head {
	background: #CCEEFF;
	height: 40px;
	padding-top: 20px;
	padding-bottom: 20px;
	margin-bottom: 5px;
	font-weight: bolder;
	margin-top: 20px;
}

.neck {
	font-size: large;
	font-weight: bold;
}

.foot {
	margin-top: 50px;
}

.div-method {
	font-size: x-large;
	padding: 2%;
	font-weight: bold;
}

textarea {
	width: 100%;
	overflow: auto;
	word-break: break-all;
	border-color: black;
}

.div-comment {
	margin-top: 3%;
	margin-left: 2%;
	margin-right: 2.5%;
	width: 95.5%;
}

.table-title {
	margin-top: 10px;
	margin-bottom: 10px;
	font-size: larger;
}
</style>
<script type="text/javascript">
	$(document).ready(function(){
		alert("核查耗时：" + ${time} +" ms !");
	});
</script>
</head>
<body>
	<%!String fontSize = WebConfig.getFontSize();%>
	<div class="head" align="center" style="font-size: <%=fontSize%>;"><%=WebConfig.PROJECTNAME + WebConfig.TITLE%></div>
	<br>
	<table class="neck">
		<tr>
			<td align="center" width="50%"><b>核查时间： <%
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date = sdf.format(new Date());
			%> <%=date%>
			</b></td>
			<td align="center" width="50%"><b>核查人：<%=WebConfig.CHECKER%></b></td>
		</tr>
	</table>
	<hr>
	<div class="div-method">一、人工核查</div>
	<table border="1">
		<tr>
			<td width="70%" align="center" class="td-height">核查项目</td>
			<td width="30%" align="center">是否通过</td>
		</tr>
		<tr>
			<td class="td-height">1.核查表结构的表名是否为规范名称。</td>
			<td align="center"><select>
					<option value="null"></option>
					<option>通过</option>
					<option>未通过</option>
					<option>不适用</option>
			</select></td>
		</tr>
		<tr>
			<td class="td-height">2.日期时间组合的变量要求变量后缀为：dy dm dd th tm ts。</td>
			<td align="center"><select>
					<option value="null"></option>
					<option>通过</option>
					<option>未通过</option>
					<option>不适用</option>
			</select></td>
		</tr>
		<tr>
			<td class="td-height">3.实验室检测项、单位及评价要求变量后缀举例为：rbc rbcu rbce。</td>
			<td align="center"><select>
					<option value="null"></option>
					<option>通过</option>
					<option>未通过</option>
					<option>不适用</option>
			</select></td>
		</tr>
		<tr>
			<td class="td-height">4.EDC课题中随访的随访状态变量名为isfw。</td>
			<td align="center"><select>
					<option value="null"></option>
					<option>通过</option>
					<option>未通过</option>
					<option>不适用</option>
			</select></td>
		</tr>
		<tr>
			<td class="td-height">5.EDC课题中备注页备注内容变量名为content。</td>
			<td align="center"><select>
					<option value="null"></option>
					<option>通过</option>
					<option>未通过</option>
					<option>不适用</option>
			</select></td>
		</tr>
		<tr>
			<td class="td-height">6.PDC课题中每表中备注变量应为bz_表名。</td>
			<td align="center"><select>
					<option value="null"></option>
					<option>通过</option>
					<option>未通过</option>
					<option>不适用</option>
			</select></td>
		</tr>
		<tr>
			<td class="td-height">7.PDC课题中多次添加的表必须设置唯一标识符变量且顺序号为1，唯一标识符变量名为表名_num，EDC课题不能设置此变量。</td>
			<td align="center"><select>
					<option value="null"></option>
					<option>通过</option>
					<option>未通过</option>
					<option>不适用</option>
			</select></td>
		</tr>
		<tr>
			<td class="td-height">8.变量字典中范围列中内容要符合规则。</td>
			<td align="center"><select>
					<option value="null"></option>
					<option>通过</option>
					<option>未通过</option>
					<option>不适用</option>
			</select></td>
		</tr>
	</table>
	<div class="div-comment">
		人工核查未通过情况说明：
		<textarea cols="50" rows="10" style="resize: none"></textarea>
	</div>
	<div class="div-method">二、程序核查</div>
	<div class="table-title" align="center">变量字典表</div>
	<table border="1">
		<tr>
			<td width="5%">dictId</td>
			<td width="10%">变量</td>
			<td width="10%">变量长度</td>

			<td width="10%">所属CRF</td>
			<td width="10%">所属CRF名称</td>

			<td width="10%">变量lable</td>
			<td width="5%">排序号</td>
			<td width="10%">变量值</td>

			<td width="5%">页码</td>
			<td width="5%">访视</td>
			<td width="10%">访视标记</td>

			<td width="10%">范围</td>
		</tr>
		<%
			String[] colorStr = { "#33CCFF", "#FFFF77", "#E8CCFF", "#DDDDDD",
					"#FFFF77", "#FFCCCC" };
			Map<String,Integer> questionCount = (Map)request.getAttribute("count");
			Map<Integer, Integer> bdIdMap = (Map) request.getAttribute("bdTag");
			List<String> dTagList = (List) request.getAttribute("dTag");
			if (dTagList != null && dTagList.size() > 0) {
				Map<String, Object> checkResult = (Map) request
						.getAttribute("data");
				for (String strD : dTagList) {
					String tempBl = "";
					
					int blColorIndex = 0;
					int lenColorIndex = 0;
					if (checkResult.containsKey(strD)) {
		%>
		<tr>
			<td colspan="12" bgcolor="<%=colorStr[5]%>"><%=checkResult.get(strD + "_msg")%>(<%=questionCount.get(strD + "_msg") %>个问题)</td>
		</tr>
		<%
			List<Dict> dicts = (List) checkResult.get(strD);
						for (Dict dict : dicts) {
							int index = -1;

							String bl = dict.getBianliang();
														
							int dictId = dict.getDictId();
							if (VarKey.CASE_DICT_HAS_BD.equals(strD)
									&& bdIdMap.containsKey(dictId)) {
								index = bdIdMap.get(dictId);
							}
		%>
		<tr>
			<%
				if (index == 1) {
			%>
			<td bgcolor="<%=colorStr[4]%>"><%=dictId%></td>
			<%
				} else {
			%>
			<td><%=dictId%></td>
			<%
				}
								if (VarKey.CASE_TABLE_VAR_NAME.equals(strD)
										|| VarKey.CASE_TABLE_START_VAR.equals(strD)
										|| VarKey.CASE_VAR_HAS_KEY.equals(strD)
										|| VarKey.CASE_ALL_VAR_REPEAT.equals(strD)
										|| VarKey.CASE_FLAG_VAR_REPEAT.equals(strD)
										|| VarKey.CASE_VAR_LENGTH_8.equals(strD)
										|| VarKey.CASE_VAR_SPEC_CODE.equals(strD)
										|| VarKey.CASE_VAR_LENGTH_10.equals(strD)
										|| index == 2) {
									if (VarKey.CASE_ALL_VAR_REPEAT.equals(strD)
											|| VarKey.CASE_FLAG_VAR_REPEAT
													.equals(strD)) {
										if (!tempBl.equals(bl)) {
											blColorIndex++;
											if (blColorIndex == 2) {
												blColorIndex = 0;
											}
										}
			%>
			<td bgcolor="<%=colorStr[blColorIndex]%>"><%=bl%></td>
			<%
				} else {
			%>
			<td bgcolor="<%=colorStr[4]%>"><%=dict.getBianliang()%></td>
			<%
				}
								} else {
			%>
			<td><%=dict.getBianliang()%></td>
			<%
				}
								if (VarKey.CASE_SAME_VAR_LENGTH.equals(strD)
										|| VarKey.CASE_SAME_VAR_LENGTH.equals(strD)
										|| index == 3) {
									if (VarKey.CASE_SAME_VAR_LENGTH.equals(strD)) {
										if (!tempBl.equals(bl)) {
											lenColorIndex++;
											if (lenColorIndex == 2) {
												lenColorIndex = 0;
											}
										}
			%>
			<td bgcolor="<%=colorStr[lenColorIndex]%>"><%=dict.getLen()%></td>
			<%
				} else {
			%>
			<td bgcolor="<%=colorStr[4]%>"><%=dict.getLen()%></td>
			<%
				}
								} else {
			%>
			<td><%=dict.getLen()%></td>
			<%
				}
								tempBl = bl;
								if (index == 4
										|| VarKey.CASE_DICT_MORE_STRUCTURE
												.equals(strD)) {
			%>
			<td bgcolor="<%=colorStr[4]%>"><%=dict.getCrf()%></td>
			<%
				} else {
			%>
			<td><%=dict.getCrf()%></td>
			<%
				}
								if (index == 5) {
			%>
			<td bgcolor="<%=colorStr[4]%>"><%=dict.getCrfname()%></td>
			<%
				} else {
			%>
			<td><%=dict.getCrfname()%></td>
			<%
				}
								if (index == 6) {
			%>
			<td bgcolor="<%=colorStr[4]%>"><%=dict.getBname()%></td>
			<%
				} else {
			%>
			<td><%=dict.getBname()%></td>
			<%
				}
								if (index == 7) {
			%>
			<td bgcolor="<%=colorStr[4]%>"><%=dict.getPaixu()%></td>
			<%
				} else {
			%>
			<td><%=dict.getPaixu()%></td>
			<%
				}
								if (VarKey.CASE_B_VAR_RULE.equals(strD)
										|| index == 8) {
			%>
			<td bgcolor="<%=colorStr[4]%>"><%=dict.getBvalue()%></td>
			<%
				} else {
			%>
			<td><%=dict.getBvalue()%></td>
			<%
				}
								if (index == 9) {
			%>
			<td bgcolor="<%=colorStr[4]%>"><%=dict.getPage()==null?"":dict.getPage()%></td>
			<%
				} else {
			%>
			<td><%=dict.getPage()==null?"":dict.getPage()%></td>
			<%
				}
								if (index == 10) {
			%>
			<td bgcolor="<%=colorStr[4]%>"><%=dict.getView()==null?"":dict.getView()%></td>
			<%
				} else {
			%>
			<td><%=dict.getView()==null?"":dict.getView()%></td>
			<%
				}
								if (VarKey.CASE_EQUAL_IS_FLAG.equals(strD)
										|| VarKey.CASE_EQUAL_NOT_FLAG.equals(strD)
										|| VarKey.CASE_Flag_SPEC_CODE.equals(strD)
										|| VarKey.CASE_FLAG_V1_V2.equals(strD)
										|| index == 11) {
			%>
			<td bgcolor="<%=colorStr[4]%>"><%=dict.getFlag()==null?"":dict.getFlag()%></td>
			<%
				} else {
			%>
			<td><%=dict.getFlag()==null?"":dict.getFlag()%></td>
			<%
				}
								if (index == 12) {
			%>
			<td bgcolor="<%=colorStr[4]%>"><%=dict.getRange()==null?"": dict.getRange()%></td>
			<%
				} else {
			%>
			<td><%=dict.getRange()==null?"": dict.getRange()%></td>
			<%
				}
			%>
		</tr>
		<%
			}
					}
				}
			} else {
		%>
		<tr>
			<td colspan="12" align="center"><b>经核查，无错误</b></td>
		</tr>
		<%
			}
		%>

	</table>
	<br>
	<br>
	<div class="table-title" align="center">表结构表</div>
	<table border="1">
		<tr>
			<td width="10%">Id</td>
			<td width="20%">表名</td>
			<td width="20%">表名描述</td>
			<td width="20%">表关键字</td>
			<td width="10%">表类型</td>
			<td width="10%">类型数字</td>
			<td width="10%">类型标识</td>
		</tr>
		<%
			List<String> sTagList = (List) request.getAttribute("sTag");
			if (sTagList != null && sTagList.size() > 0) {
				for (String strS : sTagList) {
					Map<String, Object> checkResult = (Map) request
							.getAttribute("data");
					if (checkResult.containsKey(strS)) {
		%>
		<tr>
			<td colspan="7" bgcolor="<%=colorStr[5]%>"><%=checkResult.get(strS + "_msg")%>(<%=questionCount.get(strS + "_msg") %>个问题)</td>
		</tr>
		<%
			List<Structrue> structrues = (List) checkResult
								.get(strS);
						for (Structrue s : structrues) {
		%>
		<tr>
			<td><%=s.getId()%></td>
			<%
				if (VarKey.CASE_TABLE_HAS_KEY.equals(strS)
										|| VarKey.CASE_STRUCTURE_TABLE_NAME
												.equals(strS)
										|| VarKey.CASE_STRUCTURE_MORE_DICT
												.equals(strS)) {
			%>
			<td bgcolor="<%=colorStr[4]%>"><%=s.getTableName()%></td>
			<%
				} else {
			%>
			<td><%=s.getTableName()%></td>
			<%
				}
			%>
			<td><%=s.getTableDesc()%></td>
			<%
				if (VarKey.CASE_STRUCTURE_KEY.equals(strS)) {
			%>
			<td bgcolor="<%=colorStr[4]%>"><%=s.getTableKey()%></td>
			<%
				} else {
			%>
			<td><%=s.getTableKey()%></td>
			<%
				}

								if (VarKey.CASE_TYPE_FLAG.equals(strS)) {
			%>
			<td bgcolor="<%=colorStr[4]%>"><%=s.getTablelx()%></td>
			<%
				} else {
			%>
			<td><%=s.getTablelx()%></td>
			<%
				}
			%>
			<td><%=s.getTableType()%></td>
			<%
				if (VarKey.CASE_TYPE_FLAG.equals(strS)
										|| VarKey.CASE_STRUCTURE_ITEM.equals(strS)) {
			%>
			<td bgcolor="<%=colorStr[4]%>"><%=s.getTableItem()==null?"":s.getTableItem()%></td>
			<%
				} else {
			%>
			<td><%=s.getTableItem()==null?"":s.getTableItem()%></td>
			<%
				}
			%>
		</tr>
		<%
			}
					}
				}
			} else {
		%>
		<tr>
			<td colspan="7" align="center"><b>经核查，无错误</b></td>
		</tr>
		<%
			}
		%>
	</table>
	<div class="foot"></div>
</body>
</html>