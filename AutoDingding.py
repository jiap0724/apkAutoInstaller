#coding=utf-8
import requests
import time
import json
import sys
import hashlib
import xml.dom.minidom



HEADERS = {'ua': 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.125 Safari/537.36'}
DINGDING_URL= 'https://oapi.dingtalk.com/robot/send?access_token=a31818427c52db2f59e63a1b77dc1baa274f43f8461fc0525fe03ba676794327'
RESULT_FILE= '/Users/Shared/Jenkins/Home/workspace/apkAutoInstaller/target/surefire-reports/testng-results.xml'
REPORT_URL_FILE= '/Users/Shared/Jenkins/Home/workspace/apkAutoInstaller/allure-results/executor.json'

class Message():

    def __init__(self):
        self.total=0;
        self.passed=0;
        self.failed=0;
        self.skipped=0;
        self.reportUrl="";

    #解析文件获取结果数据
    def analyze(self):
        #打开xml文档
        dom = xml.dom.minidom.parse(RESULT_FILE)
        #得到文档元素对象

        root = dom.documentElement
        self.total = root.getAttribute('total')
        self.passed = root.getAttribute('passed')
        self.failed = root.getAttribute('failed')
        self.skipped = root.getAttribute('skipped')

        #构建的次数统计在json文件中
        file = open(REPORT_URL_FILE,'r')
        self.reportUrl=json.load(file)['reportUrl']

    #发送报告
    def send_message_to_robot(self):
        url= DINGDING_URL
        message='android渠道包自动化验证执行结果:\n本次执行了{}条用例;\n成功了{}条;\n失败了{}条;\n跳过了{}条;\n查看详情请点击=> {}'.format(self.total,self.passed,self.failed,self.skipped,self.reportUrl)
        data={"msgtype":"text","text":{"content":message,"title":"android渠道包自动化验证通知"}}
        try:
            resp = requests.post(url,headers=HEADERS,json=data,timeout=(3,60))
        except:
            print ("Send Message is fail!");



if __name__ == '__main__':
    message = Message()
    message.analyze();
    message.send_message_to_robot();