<h2><a href="https://leetcode.com/problems/valid-subarrays-with-exactly-one-peak">4269. Valid Subarrays With Exactly One Peak</a></h2><h3>Medium</h3><hr><p>You are given an integer array <code>nums</code> of length <code>n</code> and an integer <code>k</code>.</p>

<p>An index <code>i</code> is a <strong>peak</strong> if:</p>

<ul>
	<li><code>0 &lt; i &lt; n - 1</code></li>
	<li><code>nums[i] &gt; nums[i - 1]</code> and <code>nums[i] &gt; nums[i + 1]</code></li>
</ul>

<p>A subarray <code>[l, r]</code> is <strong>valid</strong> if:</p>

<ul>
	<li>It contains <strong>exactly one</strong> peak at index <code>i</code> from <code>nums</code></li>
	<li><code>i - l &lt;= k</code> and <code>r - i &lt;= k</code></li>
</ul>

<p>Return an integer denoting the number of <strong>valid subarrays</strong> in <code>nums</code>.</p>
A <strong>subarray</strong> is a contiguous <b>non-empty</b> sequence of elements within an array.
<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,3,2], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Index <code>i = 1</code> is a peak because <code>nums[1] = 3</code> is greater than <code>nums[0] = 1</code> and <code>nums[2] = 2</code>.</li>
	<li>Any valid subarray must include index 1, and the distance from the peak to both ends of the subarray must not exceed <code>k = 1</code>.</li>
	<li>The valid subarrays are <code>[3]</code>, <code>[1, 3]</code>, <code>[3, 2]</code>, and <code>[1, 3, 2]</code>, so the answer is 4.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [7,8,9], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>There is no index <code>i</code> such that <code>nums[i]</code> is greater than both <code>nums[i - 1]</code> and <code>nums[i + 1]</code>.</li>
	<li>Therefore, the array contains no peak. Thus, the number of valid subarrays is 0.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,3,5,1], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Index <code>i = 2</code> is a peak because <code>nums[2] = 5</code> is greater than <code>nums[1] = 3</code> and <code>nums[3] = 1</code>.</li>
	<li>Any valid subarray must contain this peak, and the distance from the peak to both ends of the subarray must not exceed <code>k = 2</code>.</li>
	<li>The valid subarrays are <code>[5]</code>, <code>[3, 5]</code>, <code>[5, 1]</code>, <code>[3, 5, 1]</code>, <code>[4, 3, 5]</code>, and <code>[4, 3, 5, 1]</code>, so the answer is 6.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= n</code></li>
</ul>
