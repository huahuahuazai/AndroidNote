#! usr/bin/python
# coding:utf8

import url_manager
import html_downloader
import html_parser
import html_output
import gityuan_urls
from time import sleep


class SpiderMain(object):
    """docstring for SpiderMain"""

    def __init__(self):

        self.urls = url_manager.UrlManager()
        self.downloader = html_downloader.HtmlDownloader()
        self.parser = html_parser.HtmlParser()
        self.output = html_output.HtmlOutput()

    def craw(self, root_urls, file_name):

        self.urls.add_new_urls(root_urls)

        i = 0

        while self.urls.has_next():

            i = i + 1

            new_url = self.urls.get_new_url()

            html_cont = self.downloader.download2(new_url)

            new_url, new_data = self.parser.parse(new_url, html_cont)

            self.output.collect_data(new_data)

            new_file_name = file_name + ('%d.json' % i)

            self.output.output_html(new_file_name)

            #等待3s，防止太频繁访问被识别
            sleep(3)

        self.output.end(file_name, root_urls[0], i)


print('SpiderMain start work')


sp = SpiderMain()

gityuan = gityuan_urls.GitYuanUrls()

gityuan_urls = gityuan.get_urls(17)


file_name = 'gityuan_page_'

sp.craw(gityuan_urls, file_name)


print('SpiderMain end work')

print('have fun :)')
