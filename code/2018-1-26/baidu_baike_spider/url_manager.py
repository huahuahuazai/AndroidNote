#! usr/bin/python
# coding:utf8

class UrlManager(object):
	"""docstring for UrlManager"""

	def __init__(self):
		super(UrlManager, self).__init__()
		self.new_urls = []
		self.old_urls = []

	def add_new_url(self,new_url):
		if new_url == None:
			return
		if new_url not in self.new_urls and new_url not in self.old_urls:
			self.new_urls.append(new_url)

	def add_new_urls(self,new_urls):
		if new_urls == None or len(new_urls) == 0:
			return

		for url in new_urls:
			self.add_new_url(url)
		
	def has_next(self):
		return len(self.new_urls) != 0

	def get_new_url(self):
		new_url = self.new_urls.pop()
		self.old_urls.append(new_url)
		return new_url
