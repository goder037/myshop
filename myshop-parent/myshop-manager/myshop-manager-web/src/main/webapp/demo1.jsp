<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel='stylesheet' href='/webjars/bootstrap/3.3.7/css/bootstrap.min.css'>
<title>demo1</title>
</head>
<body>
	<div class="bs-docs-section">
		<h1 id="jumbotron" class="page-header">巨幕</h1>
		<p>这是一个轻量、灵活的组件，它能延伸至整个浏览器视口来展示网站上的关键内容。</p>
		<div class="bs-example">
			<div class="jumbotron">
				<h1>Hello, world!</h1>
				<p>This is a simple hero unit, a simple jumbotron-style
					component for calling extra attention to featured content or
					information.</p>
				<p>
					<a class="btn btn-primary btn-lg" href="#" role="button">Learn more</a>
				</p>
			</div>
		</div>
		<div class="zero-clipboard">
			<span class="btn-clipboard">Copy</span>
		</div>
		<div class="highlight">
			<pre>
				<code class="html">
					<span class="nt">&lt;div</span> <span class="na">class=</span><span
						class="s">"jumbotron"</span><span class="nt">&gt;</span>
  <span class="nt">&lt;h1&gt;</span>Hello, world!<span class="nt">&lt;/h1&gt;</span>
  <span class="nt">&lt;p&gt;</span>...<span class="nt">&lt;/p&gt;</span>
  <span class="nt">&lt;p&gt;&lt;a</span> <span class="na">class=</span><span
						class="s">"btn btn-primary btn-lg"</span> <span class="na">href=</span><span
						class="s">"#"</span> <span class="na">role=</span><span class="s">"button"</span><span
						class="nt">&gt;</span>Learn more<span class="nt">&lt;/a&gt;&lt;/p&gt;</span>
<span class="nt">&lt;/div&gt;</span>
</code>
			</pre>
		</div>
		<p>
			如果需要让巨幕组件的宽度与浏览器宽度一致并且没有圆角，请把此组件放在所有
			<code>.container</code>
			元素的外面，并在组件内部添加一个
			<code>.container</code>
			元素。
		</p>
		<div class="zero-clipboard">
			<span class="btn-clipboard">Copy</span>
		</div>
		<div class="highlight">
			<pre>
				<code class="html">
					<span class="nt">&lt;div</span> <span class="na">class=</span>
					<span class="s">"jumbotron"</span><span class="nt">&gt;</span>
  					<span class="nt">&lt;div</span> <span class="na">class=</span>
  					<span class="s">"container"</span><span class="nt">&gt;</span>
  					<span class="nt">&lt;/div&gt;</span>
					<span class="nt">&lt;/div&gt;</span>
				</code>
			</pre>
		</div>
	</div>
</body>
<script type="text/javascript"	src="/webjars/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript"	src="/webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</html>