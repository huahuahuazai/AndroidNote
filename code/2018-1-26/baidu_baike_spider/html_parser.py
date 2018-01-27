#! usr/bin/python
# coding:utf8

import urlparse
from bs4 import BeautifulSoup
import re

class HtmlParser(object):
	"""docstring for HtmlParser"""
	def __init__(self):
		super(HtmlParser, self).__init__()
		
	def parse(self,new_url, html_cont):

		if new_url is None or html_cont is None:
			return

		soup = BeautifulSoup(html_cont,'html.parser')
		new_urls = self._get_new_urls(new_url,soup)
		new_data = self._get_new_data(new_url,soup)


		return new_urls ,new_data

	def _get_new_urls(self,page_url,soup):

		new_urls = set()

		# <a target="_blank" href="/item/GPL">GPL</a>
		links = soup.find_all('a',href=re.compile(r'item'))

		for link in links:
			new_url = link['href']
			new_full_url = urlparse.urljoin(page_url,new_url)
			new_urls.add(new_full_url)

		return new_urls

	def _get_new_data(self,page_url,soup):

		res_data = []

		# url
		# res_data['url'] = page_url

		# gityuan.com

		# post_div_sample = r'''
		# <div class="post-preview">
  #   	<a target="_blank" href="http://gityuan.com/android">
  #       <h2>
  #           <font color="#EE0000">[置顶]</font>Android系统开篇
  #       </h2>
  #       <div class="post-content-preview">
  #           基于Android 6.0的源码，专注于分享Android系统原理、架构分析的原创文章，这是Android系列文章的开篇。
  #       </div>
  #   	</a>
  #   	<p class="post-meta">
  #       Posted by Gityuan on January 31, 2016
  #   	</p>
		# </div>'''

		post_div_nodes = soup.find_all('div',class_='post-preview')

		for post_div_node in post_div_nodes:

			# print(post_div_node)
			
			post_div_soup = BeautifulSoup(str(post_div_node),'html.parser')

			a_ = post_div_soup.a
			# print(post_div_soup.find('div',class_='post-content-preview').get_text())

			post_info = {}
			url_ = post_div_soup.a['href']
			# post_info['url'] = post_div_soup.a['href']
			if 'http://' not in url_:
				url_ = 'http://gityuan.com' + url_

			post_info['url'] = url_
			post_info['title']=post_div_soup.find('h2').get_text()
			post_info['summary']=post_div_soup.find('div',class_='post-content-preview').get_text()
			res_data.append(post_info)

		return res_data
