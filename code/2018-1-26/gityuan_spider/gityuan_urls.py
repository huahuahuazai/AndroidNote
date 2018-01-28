#!usr/bin/python
# -*- coding=utf-8 -*-


class GitYuanUrls(object):
    """docstring for GitYuanUrls"""

    def get_urls(self, num):

        urls = []

        urls.append('http://www.gityuan.com')

        for x in xrange(1, num):
            url_ = ('http://www.gityuan.com/page%d/' % x)
            urls.append(url_)

        return urls
