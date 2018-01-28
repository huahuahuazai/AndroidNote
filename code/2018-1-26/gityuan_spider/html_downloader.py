#! usr/bin/python
# coding:utf8

import string
import urllib2
# from urllib.parse import quote

import json

class HtmlDownloader(object):
    """docstring for HtmlDownloader"""

    def __init__(self):
        super(HtmlDownloader, self).__init__()

    def download(self, url):
        if url is None:
            return None
        # url_ = quote(url, safe=string.printable)
        response = urllib2.urlopen(url,timeout=300)
        # rq1 = request.Request(url)
        # rq1.add_header('user-agent','Mozilla/5.0')
        # response = request.urlopen(rq1)

        if response.getcode() != 200:
            return None
        # print(response.read())
        
        return response.read()

    def download2(self, url):
        #升级版

        if url is None:
            return None

        req_header = {'User-Agent':'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.87 Safari/537.36'}

        try:
            request = urllib2.Request(url,None,req_header)

            response = urllib2.urlopen(request,None,300)

            return response.read()
        except socket.timeout as e:
            print(type(e))
        
        return None

       
