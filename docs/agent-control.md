# Agent Control & Governance (Lab 2)

--

The enforcement is implemented using a Git `pre-commit` hook that scans staged changes and blocks commits containing `.env` files or exposed API secrets. Instructions by themselves are not enough to ensure true enforcement because of the non-deterministic nature of LLMs: an agent might follow prompt safety rules 9 out of 10 times, but the cost of the 10th failure, like commiting api secrets would cost way more than the 9 other times.