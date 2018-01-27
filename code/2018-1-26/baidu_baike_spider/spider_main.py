#! usr/bin/python
# coding:utf8

import url_manager 
import html_downloader 
import html_parser
import html_output


class SpiderMain(object):
    """docstring for SpiderMain"""

    def __init__(self):
        self.urls = url_manager.UrlManager()
        self.downloader = html_downloader.HtmlDownloader()
        self.parser = html_parser.HtmlParser()
        self.output = html_output.HtmlOutput()

    def craw(self, root_url):
        
        self.urls.add_new_url(root_url)

        new_url = self.urls.get_new_url()


        html_cont = self.downloader.download(new_url)


        new_url, new_data = self.parser.parse(new_url, html_cont)
        
        self.output.collect_data(new_data)

        self.output.output_html()

print('SpiderMain start work')
root_url = 'http://www.gityuan.com/'
sp = SpiderMain()
sp.craw(root_url)
print('SpiderMain end work')
