# Guidelines for Tales of an Epoch

The following are the guidelines that approved contributors to Tales of an Epoch must follow in order to properly work on the project. Failure to follow them will result in warnings and possibly disapproval for contribution to the project, please understand them well.

## Table of Contents

- [Work Procedures](#work-procedures)
  - [Git](#git)
    - [Branching Strategy](#branching-strategy)
    - [Commit Strategy](#commit-strategy)
    - [Workflow](#workflow)

## Work Procedures

The following are the procedures to be followed during work on this project.

### Git

Handling branching in Git for a large project, especially in the context of a game with both backend and frontend components, can be crucial for maintaining organization and collaboration. Here’s our approach to structuring:

#### Branching Strategy

Most of the work of the git management and handling will be done by the top level contributing team. As such, the following information is mainly to give an idea of the environment you'll be working with and to facilitate the easy access to the information.

Our branch system follows this organization:

- **Main Branch (main)**: This branch contains stable, production-ready code. It should only reflect completed features and bug fixes. Only top level contributors should merge and/or pull request from develop to this branch. While it is currently possible, all changes will be cleaned to follow proper procedure.
- **Development Branch (develop)**: This branch serves as the integration branch for features. You’ll pull request completed features into this branch for testing before they go to the main branch.
- **Feature Branches**: For each new feature or piece of functionality, create a separate branch from `develop`. The naming convention is `feature/feature-name`.
- **Bugfix Branches**: Similar to feature branches, but for bug fixes. The naming convention is `bugfix/bug-description`.
- **Hotfix Branches**: For critical fixes that need to go directly into the main branch. The naming convention is `hotfix/fix-description`.

#### Commit Strategy

It is very important to follow the commit strategy, as it might be possible to use tools to automatically handle versioning and to read through large numbers of commits.

For this project, we'll follow [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/#specification). If the web page is unavailable, feel free to visit the copy saved in `documentation/misc/savedpages/conventionalcommits`. For a simpler explanation or a quick refresher, feel free to visit our documentation's [CONVENTIONAL_COMMITS.md](documentation/design/work procedures/CONVENTIONAL_COMMITS.md)

#### Workflow

This is the workflow to follow as one works on this project. As main and development will be always be already created and managed by top level contributors, the usual workflow should look like this:

1. **Create a Feature Branch**:
   ```bash
   git checkout -b feature/feature-name
   ```

2. **Work on Your Feature**: Implement your changes, then commit and push them.
   ```bash
   git add .
   git commit -m "Implement login system"
   git push origin feature/feature-name
   ```
   
3. **Create a Pull Request (PR)**: After pushing the changes, make sure you create a pull request to **develop** , not **main**. This allows for code reviews and discussions. Once it is approved, a top level contributor will handle the merging.
