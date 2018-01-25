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
		res_data = {}

		# url
		res_data['url'] = page_url

		# title
		# <dd class="lemmaWgt-lemmaTitle-title">
		# <h1>Python</h1>
		# <h2>（计算机程序设计语言）</h2>
		# </dd>
		title_node = soup.find('dd',class_='lemmaWgt-lemmaTitle-title')
		res_data['h1'] = title_node.get_text()
		# summary
		# <div class="lemma-summary" label-module="lemmaSummary">...</div>
		summary_node = soup.find('div',class_='lemma-summary')
		res_data['summary'] = summary_node.get_text()

		return res_data
