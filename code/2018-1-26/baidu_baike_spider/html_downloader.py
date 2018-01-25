#! usr/bin/python
# coding:utf8

import string
import urllib2
# from urllib import request
# from urllib.parse import quote


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

        return response.read()
