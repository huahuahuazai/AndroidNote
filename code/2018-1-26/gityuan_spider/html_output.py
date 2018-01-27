#! usr/bin/python
# coding:utf8

import sys
reload(sys)
sys.setdefaultencoding('utf-8')

class HtmlOutput(object):
    """docstring for HtmlOutput"""

    def __init__(self):
        self.datas = []

    def collect_data(self, new_data):
        if new_data is not None:
            self.datas.append(new_data)

    def output_html(self):

        fout = open('output.json', 'w')
        fout.write('{')
        fout.write('"data":[')
        # fout.writable('<meta charset=\'utf-8\'>')

        for data in self.datas:
            for post_info in data:
                fout.write('{')
                fout.write(r'"url":"%s",' % post_info['url'])
                fout.write(r'"title":"%s",' % post_info['title'])
                fout.write(r'"summary":"%s"' % post_info['summary'])
                fout.write('},')
        
        fout.write(r'{}')    
        fout.write(']}')