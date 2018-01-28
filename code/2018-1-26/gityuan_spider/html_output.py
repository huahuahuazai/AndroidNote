#! usr/bin/python
# coding:utf8

import sys
import os
import time

reload(sys)
sys.setdefaultencoding('utf-8')

class HtmlOutput(object):
    """docstring for HtmlOutput"""

    def __init__(self):
        self.datas = []
        self.output_dir = 'output/'

    def collect_data(self, new_data):
        if new_data is not None:
            self.datas.append(new_data)

    def output_html(self,file_name):

        self.mkdir()

        file_name = self.output_dir + file_name

        fout = open(file_name, 'w')

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

    def end(self,file_name_start, url, num):

        self.mkdir()

        file_name = self.output_dir + 'config.json'

        file_out = open(file_name,'w')
        
        current_time = time.time()

        config_str = ('{"url":"%s","total":"%d","update_time":"%d","file_name":"%s"}' % (url,num,current_time,file_name_start))

        file_out.write(config_str)

    def mkdir(self):

        has_dir = os.path.exists(self.output_dir)

        if not has_dir:
            os.mkdir(self.output_dir)