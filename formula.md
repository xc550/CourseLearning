[TOC]

# 章节总结

## 分数说明

* 课堂听讲分数：$student.listening$
* 课堂回答问题分数：$student.answer$
* 出勤分数：$student.attendance$
* 作业分数：$student.homework$
* 实验分数：$student.experiment$
* 复习预习分数：$student.reviewandpreview$
* 总评：$student.sum = \\ student.listening \times weight_{listening} + student.answer \times weight_{answer} + \\ student.attendance \times weight_{attendance} + student.homework \times weight_{homework} + \\ student.experiment \times weight_{experiment} + student.reviewandpreview \times weight_{reviewandpreview}$

## 求平均分

$$averge = \frac{\sum_{i = 1}^n student[i].sum}{n}$$

## 求最大值

$$max = Max_{i = 1} ^ n student[i].sum$$

## 求最小值

$$min = Min_{i = 1} ^ n student[i].sum$$

## 求最高得分的学生

$student.sum$等于$max$的所有学生

## 求最低得分的学生

$student.sum$等于$min$的所有学生

## 求高于平均分的学生

$student.sum$大于$averge$的所有学生

## 求低于平均分的学生

$student.sum$小于$averge$的所有学生

## 求怀疑作弊的学生

1. $student.sum$大于$averge$
2. $student.homework$或$student.experiment$大于89

条件1和条件2同时满足的学生认定为作弊

# 课程总结

## 分数说明

* 第$j$个章节的综合分数：$student.section[j].sum$
* 综合总评：$student.avg = \frac{1}{m}\cdot \left( \sum_{i = 1}^m student.section[j].sum \times section[j].weight \right)$

## 求平均值

$$averge = \frac{\sum_{i = 1}^n student[i].avg}{n}$$

## 求最大值

$$max = Max_{i = 1}^{n}student[i].avg$$

## 求最小值

$$min = Min_{i = 1}^{n}student[i].avg$$

## 求最高得分的学生

$student.avg$等于$max$的所有学生

## 求最低得分的学生

$student.avg$等于$min$的所有学生

## 求高于平均分的学生

$student.avg$大于$averge$的所有学生

## 求低于平均分的学生

$student.averge$小于$averge$的所有学生

## 求低于平均分的学生的问题章节

1. 计算章节$j$的平均学习价值：$avg[j] = \frac{1}{n} \cdot \sum_{i = 1}^{n}student[i].section[j].sum$
2. 对于低于平均分的学生$k$，计算章节$j$的影响值：$infl[j] = \left(avg[j] - student[k].section[j].sum \right) \times section[j].weight$
3. 对于低于平均分的学生$k$，影响值$infl[j]$最大的章节为问题章节（可能多个）

## 求作弊做多的学生

统计学生作弊情况

## 求课程难点

统计问题章节，出现频率最高的三个作为课程难点