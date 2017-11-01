<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>变量字典核查报告</title>
<style type="text/css">
	table{
		margin-left: 2%; 
		margin-right: 2%;
		width: 96%;
		border-collapse:collapse;
	}
	td{
		height: 30px;
		padding: 5px;
	}
	.head{
		background: #CCEEFF;
		padding-top:20px;
		padding-bottom:20px; 
		margin-bottom: 5px;
		font-size: xx-large;
		font-weight: bolder;
		margin-top: 20px;
	}
	.neck{
		font-size:x-large;
		padding-right: 5%;
		font-weight: bold;
	}
	.div-method{
		font-size: x-large;
		padding: 2%;
		font-weight: bold;
	}
	.foot{
		margin-top: 80px;
		margin-bottom: 80px;
	}
	textarea{
    	width: 100%;
    	overflow: auto;
    	word-break: break-all;
    	border-color: black;
	}
	.div-comment{
		margin-top: 3%;
		margin-left: 2%;
		margin-right: 2.5%;
		width: 95.5%;
	}
</style>
</head>
<body>
	<div class="head" align="center">
		E201716_金仕生物_信力人工生物心脏瓣膜变量字典核查报告
	</div>
	<br>
	<div class="neck" align="right">
		测试次数：第1次
	</div>
	<hr>
	<div class="div-method">
		一、人工核查
	</div>
	<table border="1">
		<tr>
			<td width="70%" align="center">核查项目</td>
			<td width="30%" align="center">修改结果</td>
		</tr>
		<tr>
			<td>1.变量字典dictId列应为顺序数字，不能跳号、同号、空值。</td>
			<td align="center"><select>
					<option value="null"></option>
					<option value="修改">修改</option>
					<option value="未修改">未修改</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>2.核查表结构的表名是否为规范名称，规范名称见附表1。 </td>
			<td align="center"><select>	
					<option value="null"></option>
					<option value="修改">修改</option>
					<option value="未修改">未修改</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>3.核查表结构的表名为字母和数字的组合。 </td>
			<td align="center"><select>	
					<option value="null"></option>
					<option value="修改">修改</option>
					<option value="未修改">未修改</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>4.日期时间组合的变量要求变量后缀为：dy dm dd th tm ts。 </td>
			<td align="center"><select>	
					<option value="null"></option>
					<option value="修改">修改</option>
					<option value="未修改">未修改</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>5.实验室检测项、单位及评价要求变量后缀举例为：rbc rbcu rbce。 </td>
			<td align="center"><select>	
					<option value="null"></option>
					<option value="修改">修改</option>
					<option value="未修改">未修改</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>6.随访中随访状态变量名为isfw。 </td>
			<td align="center"><select>	
					<option value="null"></option>
					<option value="修改">修改</option>
					<option value="未修改">未修改</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>7.备注页备注内容变量名为content。 </td>
			<td align="center"><select>	
					<option value="null"></option>
					<option value="修改">修改</option>
					<option value="未修改">未修改</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>8.PDC课题中每表中备注变量应为bz_表名。 </td>
			<td align="center"><select>	
					<option value="null"></option>
					<option value="修改">修改</option>
					<option value="未修改">未修改</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>9.PDC课题中多次添加的表必须设置唯一标识符变量且顺序号为1，唯一标识符变量名为表名_num，EDC课题不能设置此变量。</td>
			<td align="center"><select>	
					<option value="null"></option>
					<option value="修改">修改</option>
					<option value="未修改">未修改</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>10.变量字典中范围列中内容要符合规则。</td>
			<td align="center"><select>	
					<option value="null"></option>
					<option value="修改">修改</option>
					<option value="未修改">未修改</option>
				</select>
			</td>
		</tr>
	</table>
	<div class="div-comment">
		备注：
		<textarea cols="50" rows="10" style="resize:none"></textarea>
	</div>
	<div class="div-method">
		二、程序核查
	</div>
	<table border="1">
		<tr>
			<td>  </td>
			<td>  </td>
			<td>  </td>
			<td>  </td>
		</tr>
		<tr>
			<td>  </td>
			<td>  </td>
			<td>  </td>
			<td>  </td>
		</tr>
	</table>
	<table border="1" class="foot">
		<tr>
			<td align="center" width="50%">
				核查时间：
			</td>
			<td align="center" width="50%">
				核查人签字：
			</td>
		</tr>
	</table>
</body>
</html>